package com.alibbalci.isgmobil.domain.usecase.company

import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.repository.CompanyRepository
import javax.inject.Inject

class CreateCompanyUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    suspend operator fun invoke(
        name: String,
        address: String?,
        hazardClass: String?,
        phone: String?,
        occupationalPhysician: String?
    ): Result<Company> {

        val trimmedName = name.trim()

        if (trimmedName.isBlank()) {
            return Result.failure(
                IllegalArgumentException("Şirket adı boş olamaz.")
            )
        }

        return companyRepository.createCompany(
            name = trimmedName,
            address = address?.trim()?.ifBlank { null },
            hazardClass = hazardClass?.trim()?.ifBlank { null },
            phone = phone?.trim()?.ifBlank { null },
            occupationalPhysician = occupationalPhysician?.trim()?.ifBlank { null }
        )
    }
}