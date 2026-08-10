package com.alibbalci.isgmobil.domain.model

data class ObservationAnalysis(
    val observationId: Long,
    val photoUrl: String?,
    val status: String,
    val aiDescription: String?,
    val riskCandidates: List<RiskCandidate>
)