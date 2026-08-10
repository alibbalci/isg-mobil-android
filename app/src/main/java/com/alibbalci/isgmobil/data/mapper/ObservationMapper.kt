package com.alibbalci.isgmobil.data.mapper

import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationAnalyzeResponseDto
import com.alibbalci.isgmobil.data.remote.dto.observation.RiskItemDto
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
        riskCandidates = riskCandidates.map { it.toDomain() }
    )
}