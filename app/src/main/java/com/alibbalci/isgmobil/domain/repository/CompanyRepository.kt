package com.alibbalci.isgmobil.domain.repository

import com.alibbalci.isgmobil.domain.model.Company

interface CompanyRepository {

    suspend fun getCompanies(): Result<List<Company>>

    suspend fun getCompanyById(
        id: Long
    ): Result<Company>

    suspend fun createCompany(
        name: String,
        address: String?,
        hazardClass: String?,
        phone: String?,
        occupationalPhysician: String?
    ): Result<Company>

    suspend fun updateCompany(
        id: Long,
        name: String,
        address: String?,
        hazardClass: String?,
        phone: String?,
        occupationalPhysician: String?
    ): Result<Company>

    suspend fun deleteCompany(
        id: Long
    ): Result<Unit>
}