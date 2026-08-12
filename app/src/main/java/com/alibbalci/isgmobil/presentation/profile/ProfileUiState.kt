package com.alibbalci.isgmobil.presentation.profile

data class ProfileUiState(
    val isLoading: Boolean = false,

    val fullName: String = "",
    val email: String = "",
    val role: String = "",

    val errorMessage: String? = null
)