package com.alibbalci.isgmobil.core.error

data class ApiErrorResponse(
    val timestamp: String? = null,
    val status: Int? = null,
    val code: String? = null,
    val message: String? = null,
    val path: String? = null
)