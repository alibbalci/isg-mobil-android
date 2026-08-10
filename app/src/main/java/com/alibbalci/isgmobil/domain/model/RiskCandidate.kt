package com.alibbalci.isgmobil.domain.model

data class RiskCandidate(
    val code: String,
    val name: String,
    val damage: String?,
    val suggestions: List<String>,
    val score: Double
)