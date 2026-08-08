package com.alibbalci.isgmobil.data.repository

import com.alibbalci.isgmobil.data.mapper.toDomain
import com.alibbalci.isgmobil.data.remote.api.CompanyApi
import com.alibbalci.isgmobil.data.remote.dto.company.CompanyCreateRequestDto
import com.alibbalci.isgmobil.data.remote.dto.company.CompanyUpdateRequestDto
import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyApi: CompanyApi
) : CompanyRepository {

    override suspend fun getCompanies(): Result<List<Company>> {
        return runCatching {
            companyApi
                .getCompanies()
                .map { it.toDomain() }
        }
    }

    override suspend fun getCompanyById(
        id: Long
    ): Result<Company> {
        return runCatching {
            companyApi
                .getCompanyById(id)
                .toDomain()
        }
    }

    override suspend fun createCompany(
        name: String,
        address: String?,
        hazardClass: String?,
        phone: String?,
        occupationalPhysician: String?
    ): Result<Company> {

        val request = CompanyCreateRequestDto(
            name = name,
            address = address,
            hazardClass = hazardClass,
            phone = phone,
            occupationalPhysician = occupationalPhysician
        )

        return runCatching {
            companyApi
                .createCompany(request)
                .toDomain()
        }
    }

    override suspend fun updateCompany(
        id: Long,
        name: String,
        address: String?,
        hazardClass: String?,
        phone: String?,
        occupationalPhysician: String?
    ): Result<Company> {

        val request = CompanyUpdateRequestDto(
            name = name,
            address = address,
            hazardClass = hazardClass,
            phone = phone,
            occupationalPhysician = occupationalPhysician
        )

        return runCatching {
            companyApi
                .updateCompany(
                    id = id,
                    request = request
                )
                .toDomain()
        }
    }

    override suspend fun deleteCompany(
        id: Long
    ): Result<Unit> {
        return runCatching {
            companyApi.deleteCompany(id)
        }
    }
}