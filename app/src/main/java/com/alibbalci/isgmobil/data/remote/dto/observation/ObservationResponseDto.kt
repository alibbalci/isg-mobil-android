package com.alibbalci.isgmobil.data.remote.dto.observation

data class ObservationResponseDto(
    val id: Long,
    val photoUrl: String?,
    val description: String?,
    val aiDescription: String?,

    val riskLevel: String?,
    val aiRisk: String?,
    val aiSuggestion: String?,

    val selectedRiskCode: String?,
    val selectedRiskName: String?,
    val possibleDamage: String?,
    val suggestions: String?,

    val probability: Int?,
    val severity: Int?,
    val riskScore: Int?,

    val postProbability: Int?,
    val postSeverity: Int?,
    val residualRiskScore: Int?,

    val responsiblePerson: String?,
    val dueDays: Int?,

    val status: String?,
    val createdAt: String?,
    val confirmedAt: String?,

    val companyId: Long?,
    val companyName: String?,

    val userId: Long?,
    val userFullName: String?,

    val reviewedBy: Long?,
    val reviewedAt: String?
)