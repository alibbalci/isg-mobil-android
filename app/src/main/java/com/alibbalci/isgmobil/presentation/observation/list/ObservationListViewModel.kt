package com.alibbalci.isgmobil.presentation.observation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.usecase.observation.GetObservationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservationListViewModel @Inject constructor(
    private val getObservationsUseCase: GetObservationsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(ObservationListUiState())

    val uiState: StateFlow<ObservationListUiState> =
        _uiState.asStateFlow()

    init {
        loadObservations()
    }

    fun loadObservations() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            getObservationsUseCase(
                page = 0,
                size = 20
            )
                .onSuccess { observations ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        observations = observations,
                        errorMessage = null
                    )
                }
                .onFailure { error ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage =
                            error.message
                                ?: "Gözlemler yüklenirken bir hata oluştu."
                    )
                }
        }
    }
}