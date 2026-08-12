package com.alibbalci.isgmobil.domain.repository

import com.alibbalci.isgmobil.domain.model.User

interface UserRepository {

    suspend fun getCurrentUser(): Result<User>
}