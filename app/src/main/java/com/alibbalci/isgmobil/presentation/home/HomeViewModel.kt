package com.alibbalci.isgmobil.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.usecase.observation.GetObservationsUseCase
import com.alibbalci.isgmobil.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
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

            val userDeferred = async {
                getCurrentUserUseCase()
            }

            val observationsDeferred = async {
                getObservationsUseCase(
                    page = 0,
                    size = 100
                )
            }

            val userResult =
                userDeferred.await()

            val observationsResult =
                observationsDeferred.await()

            val user =
                userResult.getOrNull()

            val observations =
                observationsResult.getOrElse {
                    emptyList()
                }

            updateDashboard(
                userName =
                    user?.fullName.orEmpty(),

                userRole =
                    user?.role.orEmpty(),

                observations =
                    observations,

                errorMessage =
                    when {

                        userResult.isFailure ->
                            "Kullanıcı bilgileri alınamadı."

                        observationsResult.isFailure ->
                            "Gözlem bilgileri alınamadı."

                        else -> null
                    }
            )
        }
    }

    private fun updateDashboard(
        userName: String,
        userRole: String,
        observations: List<Observation>,
        errorMessage: String?
    ) {

        val totalObservations =
            observations.size

        /*
         * Yüksek riskli:
         * sadece HIGH
         */
        val highRiskCount =
            observations.count { observation ->

                observation.riskLevel
                    ?.equals(
                        "HIGH",
                        ignoreCase = true
                    ) == true
            }

        /*
         * Henüz tamamlanmamış gözlemler
         */
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

        /*
         * APPROVED = tamamlandı
         */
        val resolvedCount =
            observations.count { observation ->

                observation.status
                    ?.equals(
                        "APPROVED",
                        ignoreCase = true
                    ) == true
            }

        /*
         * En son 3 gözlem
         */
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
                userRole = userRole,

                totalObservations =
                    totalObservations,

                highRiskCount =
                    highRiskCount,

                pendingCount =
                    pendingCount,

                resolvedCount =
                    resolvedCount,

                recentObservations =
                    recentObservations,

                errorMessage =
                    errorMessage
            )
    }
}