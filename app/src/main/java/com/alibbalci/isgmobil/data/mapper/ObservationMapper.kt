package com.alibbalci.isgmobil.data.mapper

import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationAnalyzeResponseDto
import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationResponseDto
import com.alibbalci.isgmobil.data.remote.dto.observation.RiskItemDto
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.model.ObservationAnalysis
import com.alibbalci.isgmobil.domain.model.RiskCandidate

fun RiskItemDto.toDomain(): RiskCandidate {
    return RiskCandidate(
        code = code,
        name = name,
        damage = damage,
        suggestions = suggestions,
        score = score
    )
}

fun ObservationAnalyzeResponseDto.toDomain(): ObservationAnalysis {
    return ObservationAnalysis(
        observationId = observationId,
        photoUrl = photoUrl,
        status = status,
        aiDescription = aiDescription,
        riskCandidates = riskCandidates.map {
            it.toDomain()
        }
    )
}

fun ObservationResponseDto.toDomain(): Observation {
    return Observation(
        id = id,
        photoUrl = photoUrl,
        description = description,
        aiDescription = aiDescription,

        riskLevel = riskLevel,
        selectedRiskCode = selectedRiskCode,
        selectedRiskName = selectedRiskName,
        possibleDamage = possibleDamage,
        suggestions = suggestions,

        probability = probability,
        severity = severity,
        riskScore = riskScore,

        postProbability = postProbability,
        postSeverity = postSeverity,
        residualRiskScore = residualRiskScore,

        responsiblePerson = responsiblePerson,
        dueDays = dueDays,

        status = status,
        createdAt = createdAt,
        confirmedAt = confirmedAt,

        companyId = companyId,
        companyName = companyName,

        userId = userId,
        userFullName = userFullName
    )
}