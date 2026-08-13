package com.alibbalci.isgmobil.presentation.observation.create

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.usecase.company.GetCompaniesUseCase
import com.alibbalci.isgmobil.domain.usecase.observation.AnalyzeObservationUseCase
import com.alibbalci.isgmobil.domain.usecase.observation.ConfirmObservationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ObservationCreateViewModel @Inject constructor(
    private val getCompaniesUseCase: GetCompaniesUseCase,
    private val analyzeObservationUseCase: AnalyzeObservationUseCase,
    private val confirmObservationUseCase: ConfirmObservationUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(ObservationCreateUiState())

    val uiState: StateFlow<ObservationCreateUiState> =
        _uiState.asStateFlow()

    init {
        loadCompanies()
    }

    fun loadCompanies() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoadingCompanies = true,
                errorMessage = null
            )

            getCompaniesUseCase()
                .onSuccess { companies ->

                    _uiState.value = _uiState.value.copy(
                        isLoadingCompanies = false,
                        companies = companies,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isLoadingCompanies = false,
                        errorMessage =
                            error.message
                                ?: "Şirketler yüklenirken bir hata oluştu."
                    )
                }
        }
    }

    fun selectCompany(company: Company) {
        _uiState.value = _uiState.value.copy(
            selectedCompany = company,
            errorMessage = null
        )
    }

    fun clearSelectedCompany() {
        _uiState.value = _uiState.value.copy(
            selectedCompany = null
        )
    }

    fun onPhotoSelected(uri: Uri) {
        _uiState.value = _uiState.value.copy(
            photoUri = uri,
            analysisResult = null,
            selectedSuggestion = null,
            errorMessage = null
        )
    }

    fun removePhoto() {
        _uiState.value = _uiState.value.copy(
            photoUri = null,
            analysisResult = null
        )
    }

    fun setError(message: String) {
        _uiState.value = _uiState.value.copy(
            errorMessage = message
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(
            errorMessage = null
        )
    }

    fun analyzePhoto(
        file: MultipartBody.Part,
        companyId: RequestBody
    ) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isAnalyzing = true,
                errorMessage = null
            )

            analyzeObservationUseCase(
                file = file,
                companyId = companyId
            )
                .onSuccess { analysis ->

                    _uiState.value = _uiState.value.copy(
                        isAnalyzing = false,
                        analysisResult = analysis,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isAnalyzing = false,
                        analysisResult = null,
                        errorMessage =
                            error.message
                                ?: "Fotoğraf analiz edilirken bir hata oluştu."
                    )
                }
        }
    }

    fun selectSuggestion(suggestion: String) {
        _uiState.value = _uiState.value.copy(
            selectedSuggestion = suggestion,
            errorMessage = null
        )
    }

    fun selectRisk(
        riskCode: String
    ) {

        _uiState.value =
            _uiState.value.copy(
                selectedRiskCode = riskCode,
                selectedSuggestion = null,
                errorMessage = null
            )
    }

    fun confirmObservation() {
        val currentState = _uiState.value

        val observationId = currentState.analysisResult?.observationId
        val selectedRiskCode = currentState.selectedRiskCode
        val description = currentState.analysisResult?.aiDescription

        if (observationId == null) {
            _uiState.value = currentState.copy(
                confirmationError = "Onaylanacak gözlem bulunamadı."
            )
            return
        }

        if (selectedRiskCode.isNullOrBlank()) {
            _uiState.value = currentState.copy(
                confirmationError = "Lütfen bir risk seçin."
            )
            return
        }

        if (currentState.isConfirming) {
            return
        }

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isConfirming = true,
                confirmationError = null,
                confirmationSuccess = false
            )

            try {
                confirmObservationUseCase(
                    observationId = observationId,
                    description = description.orEmpty(),
                    selectedRiskCode = selectedRiskCode
                )

                _uiState.value = _uiState.value.copy(
                    isConfirming = false,
                    confirmationSuccess = true,
                    confirmationError = null
                )

            } catch (e: Exception) {

                _uiState.value = _uiState.value.copy(
                    isConfirming = false,
                    confirmationSuccess = false,
                    confirmationError = e.message ?: "Gözlem onaylanamadı."
                )
            }
        }
    }






}