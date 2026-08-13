package com.alibbalci.isgmobil.data.repository

import com.alibbalci.isgmobil.data.mapper.toDomain
import com.alibbalci.isgmobil.data.remote.api.ObservationApi
import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationConfirmRequestDto
import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.model.ObservationAnalysis
import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ObservationRepositoryImpl @Inject constructor(
    private val observationApi: ObservationApi
) : ObservationRepository {

    override suspend fun analyzeObservation(
        file: MultipartBody.Part,
        companyId: RequestBody
    ): Result<ObservationAnalysis> {

        return runCatching {
            observationApi
                .analyzeObservation(
                    file = file,
                    companyId = companyId
                )
                .toDomain()
        }
    }

    override suspend fun getObservations(
        page: Int,
        size: Int
    ): Result<List<Observation>> {

        return runCatching {

            observationApi
                .getObservations(
                    page = page,
                    size = size
                )
                .content
                .map { observationDto ->
                    observationDto.toDomain()
                }
        }
    }

    override suspend fun confirmObservation(
        observationId: Long,
        description: String,
        selectedRiskCode: String
    ): Result<Observation> {

        return runCatching {

            val request = ObservationConfirmRequestDto(
                description = description,
                selectedRiskCode = selectedRiskCode
            )

            observationApi
                .confirmObservation(
                    observationId = observationId,
                    request = request
                )
                .toDomain()
        }
    }

    override suspend fun getObservationById(observationId: Long): Result<Observation> {
        return runCatching {
            observationApi
                .getObservationById(
                    observationId = observationId
                )
                .toDomain()
        }
    }

}
