package com.alibbalci.isgmobil.domain.repository

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun register(
        fullName: String,
        email: String,
        password: String
    ): Result<Unit>
}