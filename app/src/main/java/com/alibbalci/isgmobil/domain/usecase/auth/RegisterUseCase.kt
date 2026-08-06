package com.alibbalci.isgmobil.domain.usecase.auth

import com.alibbalci.isgmobil.domain.repository.AuthRepository
import jakarta.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit> {

        val trimmedFullName = fullName.trim()
        val trimmedEmail = email.trim()

        if (trimmedFullName.isBlank()) {
            return Result.failure(
                RegisterValidationError.EmptyFullName
            )
        }

        if (trimmedEmail.isBlank()) {
            return Result.failure(
                RegisterValidationError.EmptyEmail
            )
        }

        if (!isValidEmail(trimmedEmail)) {
            return Result.failure(
                RegisterValidationError.InvalidEmail
            )
        }

        if (password.isBlank()) {
            return Result.failure(
                RegisterValidationError.EmptyPassword
            )
        }

        if (password.length < 6) {
            return Result.failure(
                RegisterValidationError.ShortPassword
            )
        }

        return authRepository.register(
            fullName = trimmedFullName,
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