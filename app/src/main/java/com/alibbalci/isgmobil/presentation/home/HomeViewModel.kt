package com.alibbalci.isgmobil.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.core.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    fun logout(
        onLogoutSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            sessionManager.logout()
            onLogoutSuccess()
        }
    }
}