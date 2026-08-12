package com.alibbalci.isgmobil.data.mapper

import com.alibbalci.isgmobil.data.remote.dto.user.UserResponseDto
import com.alibbalci.isgmobil.domain.model.User

fun UserResponseDto.toDomain(): User {

    return User(
        id = id,
        fullName = fullName,
        email = email,
        role = role
    )
}