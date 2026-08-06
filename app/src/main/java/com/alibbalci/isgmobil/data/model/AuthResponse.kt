package com.alibbalci.isgmobil.data.model

data class AuthResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val user: UserResponse
)
