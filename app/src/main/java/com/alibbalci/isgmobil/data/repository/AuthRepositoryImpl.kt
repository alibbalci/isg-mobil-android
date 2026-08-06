package com.alibbalci.isgmobil.data.repository

import com.alibbalci.isgmobil.core.session.TokenManager
import com.alibbalci.isgmobil.data.model.LoginRequest
import com.alibbalci.isgmobil.data.model.RegisterRequest
import com.alibbalci.isgmobil.data.remote.api.AuthApi
import com.alibbalci.isgmobil.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            authApi.register(
                RegisterRequest(
                    fullName = fullName,
                    email = email,
                    password = password
                )
            )

            Result.success(Unit)

        } catch (exception: CancellationException) {
            throw exception

        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val authResponse = authApi.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            tokenManager.saveToken(
                authResponse.accessToken
            )

            Result.success(Unit)

        } catch (exception: CancellationException) {
            throw exception

        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}