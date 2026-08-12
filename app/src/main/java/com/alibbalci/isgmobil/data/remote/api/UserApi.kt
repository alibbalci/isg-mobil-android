package com.alibbalci.isgmobil.data.remote.api

import com.alibbalci.isgmobil.data.remote.dto.user.UserResponseDto
import retrofit2.http.GET

interface UserApi {

    @GET("api/users/me")
    suspend fun getCurrentUser(): UserResponseDto
}