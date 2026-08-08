package com.alibbalci.isgmobil.domain.usecase.company

import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompanyByIdUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    suspend operator fun invoke(
        companyId: Long
    ): Result<Company> {
        return companyRepository.getCompanyById(companyId)
    }
}