package com.alibbalci.isgmobil.data.remote.dto.observation

data class RiskItemDto(
    val code: String,
    val name: String,
    val damage: String?,
    val suggestions: List<String>,
    val score: Double
)