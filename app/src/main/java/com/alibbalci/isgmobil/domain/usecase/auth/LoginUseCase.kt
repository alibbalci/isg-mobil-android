package com.alibbalci.isgmobil.domain.usecase.auth

import com.alibbalci.isgmobil.domain.repository.AuthRepository
import jakarta.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {

        val trimmedEmail = email.trim()

        if (trimmedEmail.isBlank()) {
            return Result.failure(
                LoginValidationError.EmptyEmail
            )
        }

        if (!isValidEmail(trimmedEmail)) {
            return Result.failure(
                LoginValidationError.InvalidEmail
            )
        }

        if (password.isBlank()) {
            return Result.failure(
                LoginValidationError.EmptyPassword
            )
        }

        return authRepository.login(
            email = trimmedEmail,
            password = password
        )
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

        return email.matches(emailRegex)
    }
}