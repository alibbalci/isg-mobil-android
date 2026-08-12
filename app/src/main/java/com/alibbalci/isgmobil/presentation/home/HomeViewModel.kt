package com.alibbalci.isgmobil.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.usecase.observation.GetObservationsUseCase
import com.alibbalci.isgmobil.presentation.session.SessionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getObservationsUseCase: GetObservationsUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            HomeUiState()
        )

    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

            getObservationsUseCase(
                page = 0,
                size = 100
            )
                .onSuccess { observations ->

                    updateDashboard(
                        observations
                    )
                }
                .onFailure { exception ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage =
                                exception.message
                                    ?: "Ana sayfa verileri alınamadı."
                        )
                }
        }
    }

    private fun updateDashboard(
        observations: List<Observation>
    ) {

        val userName =
            observations
                .firstOrNull()
                ?.userFullName
                .orEmpty()

        val totalObservations =
            observations.size

        val highRiskCount =
            observations.count { observation ->

                observation.riskLevel
                    ?.equals(
                        "HIGH",
                        ignoreCase = true
                    ) == true
            }

        val pendingCount =
            observations.count { observation ->

                observation.status
                    ?.uppercase() in listOf(
                    "PENDING_AI",
                    "AI_ANALYZED",
                    "CONFIRMED",
                    "REVIEWED"
                )
            }

        val resolvedCount =
            observations.count { observation ->

                observation.status
                    ?.equals(
                        "APPROVED",
                        ignoreCase = true
                    ) == true
            }

        val recentObservations =
            observations
                .sortedByDescending {
                    it.createdAt
                }
                .take(3)

        _uiState.value =
            HomeUiState(
                isLoading = false,
                userName = userName,
                totalObservations = totalObservations,
                highRiskCount = highRiskCount,
                pendingCount = pendingCount,
                resolvedCount = resolvedCount,
                recentObservations = recentObservations,
                errorMessage = null
            )
    }
}