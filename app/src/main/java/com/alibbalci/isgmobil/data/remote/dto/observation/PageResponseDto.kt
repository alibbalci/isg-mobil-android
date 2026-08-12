package com.alibbalci.isgmobil.data.remote.dto.observation

data class PageResponseDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val number: Int,
    val size: Int,
    val first: Boolean,
    val last: Boolean
)