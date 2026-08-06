package com.alibbalci.isgmobil.core.error

sealed interface AppError {
    //belirli liste oldugu icin sealed

    data object Network : AppError

    data object Unauthorized : AppError

    data object NotFound : AppError

    data object Conflict : AppError

    data object ServerUnavailable : AppError

    data class Validation(
        val message: String
    ) : AppError

    data class Unknown(
        val message: String?
    ) : AppError
}