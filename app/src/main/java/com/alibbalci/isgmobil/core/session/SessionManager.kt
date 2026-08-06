package com.alibbalci.isgmobil.core.session

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val tokenManager: TokenManager
) {

    val isLoggedIn: Flow<Boolean> =
        tokenManager.token.map { token ->
            !token.isNullOrBlank()
        }

    suspend fun logout() {
        tokenManager.clearToken()
    }
}