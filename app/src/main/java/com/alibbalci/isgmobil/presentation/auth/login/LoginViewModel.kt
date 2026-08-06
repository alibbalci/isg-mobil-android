package com.alibbalci.isgmobil.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.core.error.ErrorMapper
import com.alibbalci.isgmobil.core.error.toUserMessage
import com.alibbalci.isgmobil.domain.usecase.auth.LoginUseCase
import com.alibbalci.isgmobil.domain.usecase.auth.LoginValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())

    val uiState: StateFlow<LoginUiState> =
        _uiState.asStateFlow()

    private val _effect = Channel<LoginEffect>(
        capacity = Channel.BUFFERED
    )

    val effect = _effect.receiveAsFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null,
            generalError = null
        )
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null,
            generalError = null
        )
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible =
                !_uiState.value.isPasswordVisible
        )
    }

    fun login() {
        if (_uiState.value.isLoading) {
            return
        }

        val email = _uiState.value.email
        val password = _uiState.value.password

        viewModelScope.launch {
            setLoadingState()

            val result = loginUseCase(
                email = email,
                password = password
            )

            result
                .onSuccess {
                    handleLoginSuccess()
                }
                .onFailure { exception ->
                    handleLoginError(exception)
                }
        }
    }

    private fun setLoadingState() {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            emailError = null,
            passwordError = null,
            generalError = null
        )
    }

    private suspend fun handleLoginSuccess() {
        _uiState.value = _uiState.value.copy(
            isLoading = false
        )

        _effect.send(
            LoginEffect.NavigateToHome
        )
    }

    private fun handleLoginError(
        exception: Throwable
    ) {
        _uiState.value = when (exception) {

            LoginValidationError.EmptyEmail,
            LoginValidationError.InvalidEmail -> {
                _uiState.value.copy(
                    isLoading = false,
                    emailError = exception.message
                )
            }

            LoginValidationError.EmptyPassword -> {
                _uiState.value.copy(
                    isLoading = false,
                    passwordError = exception.message
                )
            }

            else -> {
                val appError = errorMapper.map(exception)

                _uiState.value.copy(
                    isLoading = false,
                    generalError = appError.toUserMessage()
                )
            }
        }
    }
}