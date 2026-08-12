package com.alibbalci.isgmobil.data.remote.api

import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationAnalyzeResponseDto
import com.alibbalci.isgmobil.data.remote.dto.observation.ObservationResponseDto
import com.alibbalci.isgmobil.data.remote.dto.observation.PageResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ObservationApi {

    @Multipart
    @POST("api/observations/analyze")
    suspend fun analyzeObservation(
        @Part file: MultipartBody.Part,
        @Part("companyId") companyId: RequestBody
    ): ObservationAnalyzeResponseDto

    @GET("api/observations")
    suspend fun getObservations(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 100
    ): PageResponseDto<ObservationResponseDto>

}