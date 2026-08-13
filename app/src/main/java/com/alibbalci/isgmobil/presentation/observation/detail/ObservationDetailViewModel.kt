package com.alibbalci.isgmobil.presentation.observation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.usecase.observation.GetObservationDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservationDetailViewModel @Inject constructor(
    private val getObservationDetailUseCase: GetObservationDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ObservationDetailUiState()
        )

    val uiState: StateFlow<ObservationDetailUiState> =
        _uiState.asStateFlow()

    private val observationId: Long =
        checkNotNull(
            savedStateHandle["observationId"]
        )

    init {
        loadObservation()
    }

    fun loadObservation() {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

            getObservationDetailUseCase(
                observationId = observationId
            )
                .onSuccess { observation ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            observation = observation,
                            errorMessage = null
                        )
                }

                .onFailure { error ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            observation = null,
                            errorMessage =
                                error.message
                                    ?: "Gözlem detayı yüklenirken bir hata oluştu."
                        )
                }
        }
    }
}