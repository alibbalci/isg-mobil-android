package com.alibbalci.isgmobil.presentation.observation.detail

import com.alibbalci.isgmobil.domain.model.Observation

data class ObservationDetailUiState(
    val isLoading: Boolean = false,
    val observation: Observation? = null,
    val errorMessage: String? = null
)
