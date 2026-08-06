package com.alibbalci.isgmobil.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alibbalci.isgmobil.core.error.ErrorMapper
import com.alibbalci.isgmobil.core.error.toUserMessage
import com.alibbalci.isgmobil.domain.usecase.auth.RegisterUseCase
import com.alibbalci.isgmobil.domain.usecase.auth.RegisterValidationError
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val errorMapper: ErrorMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())

    val uiState: StateFlow<RegisterUiState> =
        _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>(
        capacity = Channel.BUFFERED
    )

    val effect = _effect.receiveAsFlow()

    fun onFullNameChange(fullName: String) {
        _uiState.value = _uiState.value.copy(
            fullName = fullName,
            fullNameError = null,
            generalError = null
        )
    }

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

    fun register() {
        if (_uiState.value.isLoading) {
            return
        }

        val currentState = _uiState.value

        viewModelScope.launch {
            setLoadingState(currentState)

            registerUseCase(
                fullName = currentState.fullName,
                email = currentState.email,
                password = currentState.password
            )
                .onSuccess {
                    handleRegisterSuccess()
                }
                .onFailure { exception ->
                    handleRegisterError(exception)
                }
        }
    }

    private fun setLoadingState(
        currentState: RegisterUiState
    ) {
        _uiState.value = currentState.copy(
            isLoading = true,
            fullNameError = null,
            emailError = null,
            passwordError = null,
            generalError = null
        )
    }

    private suspend fun handleRegisterSuccess() {
        _uiState.value = _uiState.value.copy(
            isLoading = false
        )

        _effect.send(
            RegisterEffect.NavigateToLogin
        )
    }

    private fun handleRegisterError(
        exception: Throwable
    ) {
        _uiState.value = when (exception) {

            RegisterValidationError.EmptyFullName -> {
                _uiState.value.copy(
                    isLoading = false,
                    fullNameError = exception.message
                )
            }

            RegisterValidationError.EmptyEmail,
            RegisterValidationError.InvalidEmail -> {
                _uiState.value.copy(
                    isLoading = false,
                    emailError = exception.message
                )
            }

            RegisterValidationError.EmptyPassword,
            RegisterValidationError.ShortPassword -> {
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