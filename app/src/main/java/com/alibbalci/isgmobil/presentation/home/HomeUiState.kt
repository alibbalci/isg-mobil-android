package com.alibbalci.isgmobil.presentation.home

import com.alibbalci.isgmobil.domain.model.Observation

data class HomeUiState(
    val isLoading: Boolean = false,

    val userName: String = "",

    val totalObservations: Int = 0,
    val highRiskCount: Int = 0,
    val pendingCount: Int = 0,
    val resolvedCount: Int = 0,

    val recentObservations: List<Observation> = emptyList(),

    val errorMessage: String? = null
)