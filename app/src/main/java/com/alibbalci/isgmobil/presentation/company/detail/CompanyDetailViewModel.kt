package com.alibbalci.isgmobil.presentation.company.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.usecase.company.GetCompanyByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyDetailViewModel @Inject constructor(
    private val getCompanyByIdUseCase: GetCompanyByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyDetailUiState())

    val uiState: StateFlow<CompanyDetailUiState> =
        _uiState.asStateFlow()

    private val companyId: Long =
        checkNotNull(savedStateHandle["companyId"])

    init {
        loadCompany()
    }

    fun loadCompany() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            getCompanyByIdUseCase(companyId)
                .onSuccess { company ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        company = company,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        company = null,
                        errorMessage = error.message
                            ?: "Şirket bilgileri yüklenemedi."
                    )
                }
        }
    }
}