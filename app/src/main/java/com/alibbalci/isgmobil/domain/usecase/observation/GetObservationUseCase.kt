package com.alibbalci.isgmobil.domain.usecase.observation

import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import javax.inject.Inject

class GetObservationsUseCase @Inject constructor(
    private val observationRepository: ObservationRepository
) {

    suspend operator fun invoke(
        page: Int = 0,
        size: Int = 100
    ): Result<List<Observation>> {

        return observationRepository.getObservations(
            page = page,
            size = size
        )
    }
}