package com.alibbalci.isgmobil.presentation.auth.register

sealed interface RegisterEffect {

    data object NavigateToLogin : RegisterEffect
}