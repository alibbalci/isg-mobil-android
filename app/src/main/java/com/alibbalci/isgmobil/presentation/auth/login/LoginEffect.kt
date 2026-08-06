package com.alibbalci.isgmobil.presentation.auth.login

sealed interface LoginEffect {

    data object NavigateToHome : LoginEffect
}