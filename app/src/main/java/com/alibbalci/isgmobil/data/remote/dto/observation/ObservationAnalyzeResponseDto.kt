package com.alibbalci.isgmobil.data.remote.dto.observation

data class ObservationAnalyzeResponseDto(
    val observationId: Long,
    val photoUrl: String?,
    val status: String,
    val aiDescription: String?,
    val riskCandidates: List<RiskItemDto>
)