package com.alibbalci.isgmobil.domain.usecase.observation

import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import javax.inject.Inject

class GetObservationDetailUseCase @Inject constructor(
    private val repository: ObservationRepository
) {

    suspend operator fun invoke(
        observationId: Long
    ): Result<Observation> {

        return repository.getObservationById(
            observationId = observationId
        )
    }
}