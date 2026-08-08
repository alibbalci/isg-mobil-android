package com.alibbalci.isgmobil.presentation.company.detail

import com.alibbalci.isgmobil.domain.model.Company

data class CompanyDetailUiState(
    val isLoading: Boolean = false,
    val company: Company? = null,
    val errorMessage: String? = null
)