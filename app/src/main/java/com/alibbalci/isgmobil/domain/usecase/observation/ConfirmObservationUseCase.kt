package com.alibbalci.isgmobil.domain.usecase.observation

import com.alibbalci.isgmobil.domain.model.Observation

import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import javax.inject.Inject

class ConfirmObservationUseCase @Inject constructor(
    private val repository: ObservationRepository
) {

    suspend operator fun invoke(
        observationId: Long,
        description: String,
        selectedRiskCode: String
    ): Result<Observation> {

        return repository.confirmObservation(
            observationId = observationId,
            description = description,
            selectedRiskCode = selectedRiskCode
        )
    }
}