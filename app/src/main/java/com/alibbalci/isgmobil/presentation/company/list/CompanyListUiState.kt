package com.alibbalci.isgmobil.presentation.company.list

import com.alibbalci.isgmobil.domain.model.Company

data class CompanyListUiState(
    val isLoading: Boolean = false,
    val companies: List<Company> = emptyList(),
    val errorMessage: String? = null
)