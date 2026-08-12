package com.alibbalci.isgmobil.data.repository

import com.alibbalci.isgmobil.data.mapper.toDomain
import com.alibbalci.isgmobil.data.remote.api.UserApi
import com.alibbalci.isgmobil.domain.model.User
import com.alibbalci.isgmobil.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getCurrentUser(): Result<User> {

        return runCatching {
            userApi
                .getCurrentUser()
                .toDomain()
        }
    }
}