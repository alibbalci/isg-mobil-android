package com.alibbalci.isgmobil.core.network

import android.util.Log
import com.alibbalci.isgmobil.core.session.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val originalRequest = chain.request()

        val token = runBlocking {
            tokenManager.token.first()
        }

        if (token.isNullOrBlank()) {
            Log.d(
                "AUTH_INTERCEPTOR",
                "Token yok. Header eklenmedi. URL: ${originalRequest.url}"
            )

            return chain.proceed(originalRequest)
        }

        val authenticatedRequest = originalRequest
            .newBuilder()
            .header(
                "Authorization",
                "Bearer $token"
            )
            .build()

        Log.d(
            "AUTH_INTERCEPTOR",
            "Authorization header eklendi. URL: ${authenticatedRequest.url}"
        )

        Log.d(
            "AUTH_INTERCEPTOR",
            "Header kontrolü: ${
                authenticatedRequest.header("Authorization") != null
            }"
        )

        return chain.proceed(authenticatedRequest)
    }
}