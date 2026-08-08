package com.alibbalci.isgmobil.presentation.company.create

data class CompanyCreateUiState(
    val name: String = "",
    val address: String = "",
    val hazardClass: String = "",
    val phone: String = "",
    val occupationalPhysician: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isCreated: Boolean = false
)