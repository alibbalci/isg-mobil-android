package com.alibbalci.isgmobil.presentation.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.core.session.SessionManager

import com.alibbalci.isgmobil.session.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    sessionManager: SessionManager
) : ViewModel() {

    val sessionState: StateFlow<SessionState> =
        sessionManager.isLoggedIn
            .map { isLoggedIn ->
                if (isLoggedIn) {
                    SessionState.LoggedIn
                } else {
                    SessionState.LoggedOut
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = SessionState.Loading
            )
}