package com.alibbalci.isgmobil.domain.usecase.observation

import com.alibbalci.isgmobil.domain.model.ObservationAnalysis
import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class AnalyzeObservationUseCase @Inject constructor(
    private val observationRepository: ObservationRepository
) {

    suspend operator fun invoke(
        file: MultipartBody.Part,
        companyId: RequestBody
    ): Result<ObservationAnalysis> {

        return observationRepository.analyzeObservation(
            file = file,
            companyId = companyId
        )
    }
}