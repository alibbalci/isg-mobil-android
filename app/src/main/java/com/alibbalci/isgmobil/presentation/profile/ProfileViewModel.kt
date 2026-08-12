package com.alibbalci.isgmobil.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.core.session.SessionManager
import com.alibbalci.isgmobil.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ProfileUiState()
        )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

            getCurrentUserUseCase()
                .onSuccess { user ->

                    _uiState.value =
                        ProfileUiState(
                            isLoading = false,
                            fullName = user.fullName,
                            email = user.email,
                            role = user.role
                        )
                }
                .onFailure { exception ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            errorMessage =
                                exception.message
                                    ?: "Profil bilgileri alınamadı."
                        )
                }
        }
    }
    fun logout(
        onLogoutSuccess: () -> Unit
    ) {

        viewModelScope.launch {

            sessionManager.logout()

            onLogoutSuccess()
        }
    }
}