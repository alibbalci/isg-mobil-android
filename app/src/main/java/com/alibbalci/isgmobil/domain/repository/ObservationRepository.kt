package com.alibbalci.isgmobil.domain.repository

import com.alibbalci.isgmobil.domain.model.Observation
import com.alibbalci.isgmobil.domain.model.ObservationAnalysis
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface ObservationRepository {

    suspend fun analyzeObservation(
        file: MultipartBody.Part,
        companyId: RequestBody
    ): Result<ObservationAnalysis>

    suspend fun getObservations(
        page: Int = 0,
        size: Int = 100
    ): Result<List<Observation>>
}