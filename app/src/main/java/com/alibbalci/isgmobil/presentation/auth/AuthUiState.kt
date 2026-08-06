package com.alibbalci.isgmobil.presentation.auth

data class AuthUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val resultText: String = "",
    val isLoading: Boolean = false
)