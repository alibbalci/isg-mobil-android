package com.alibbalci.isgmobil.domain.usecase.user

import com.alibbalci.isgmobil.domain.model.User
import com.alibbalci.isgmobil.domain.repository.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): Result<User> {
        return userRepository.getCurrentUser()
    }
}