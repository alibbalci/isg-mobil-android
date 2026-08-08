package com.alibbalci.isgmobil.domain.usecase.company

import com.alibbalci.isgmobil.domain.model.Company
import com.alibbalci.isgmobil.domain.repository.CompanyRepository
import javax.inject.Inject

class GetCompaniesUseCase @Inject constructor(
    private val companyRepository: CompanyRepository
) {

    suspend operator fun invoke(): Result<List<Company>> {
        return companyRepository.getCompanies()
    }
}