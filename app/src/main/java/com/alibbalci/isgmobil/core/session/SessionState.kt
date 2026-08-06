package com.alibbalci.isgmobil.session

sealed interface SessionState {

    data object Loading : SessionState

    data object LoggedIn : SessionState

    data object LoggedOut : SessionState
}