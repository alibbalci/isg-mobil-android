package com.alibbalci.isgmobil.data.remote.api

import com.alibbalci.isgmobil.data.model.AuthResponse
import com.alibbalci.isgmobil.data.model.LoginRequest
import com.alibbalci.isgmobil.data.model.RegisterRequest
import com.alibbalci.isgmobil.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): UserResponse

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse
}