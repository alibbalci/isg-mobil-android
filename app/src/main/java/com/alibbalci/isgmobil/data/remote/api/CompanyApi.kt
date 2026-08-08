package com.alibbalci.isgmobil.data.remote.api

import com.alibbalci.isgmobil.data.remote.dto.company.CompanyCreateRequestDto
import com.alibbalci.isgmobil.data.remote.dto.company.CompanyDto
import com.alibbalci.isgmobil.data.remote.dto.company.CompanyUpdateRequestDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CompanyApi {

    @GET("api/companies")
    suspend fun getCompanies(): List<CompanyDto>

    @GET("api/companies/{id}")
    suspend fun getCompanyById(
        @Path("id") id: Long
    ): CompanyDto

    @POST("api/companies")
    suspend fun createCompany(
        @Body request: CompanyCreateRequestDto
    ): CompanyDto

    @PUT("api/companies/{id}")
    suspend fun updateCompany(
        @Path("id") id: Long,
        @Body request: CompanyUpdateRequestDto
    ): CompanyDto

    @DELETE("api/companies/{id}")
    suspend fun deleteCompany(
        @Path("id") id: Long
    )
}