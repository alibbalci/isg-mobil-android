package com.alibbalci.isgmobil.core.photo

sealed class PhotoValidationResult {

    data object Valid : PhotoValidationResult()

    data class Invalid(
        val message: String
    ) : PhotoValidationResult()
}