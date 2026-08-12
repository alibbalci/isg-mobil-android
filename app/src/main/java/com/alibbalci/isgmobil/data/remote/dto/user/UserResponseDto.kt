package com.alibbalci.isgmobil.data.remote.dto.user

data class UserResponseDto(
    val id: Long,
    val fullName: String,
    val email: String,
    val role: String
)