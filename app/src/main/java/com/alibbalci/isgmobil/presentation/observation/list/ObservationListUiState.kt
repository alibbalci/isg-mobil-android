package com.alibbalci.isgmobil.presentation.observation.list

import com.alibbalci.isgmobil.domain.model.Observation

data class ObservationListUiState(
    val isLoading: Boolean = false,
    val observations: List<Observation> = emptyList(),
    val errorMessage: String? = null
) {

    val isEmpty: Boolean
        get() = !isLoading &&
                errorMessage == null &&
                observations.isEmpty()
}