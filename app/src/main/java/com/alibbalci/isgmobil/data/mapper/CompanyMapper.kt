package com.alibbalci.isgmobil.data.mapper

import com.alibbalci.isgmobil.data.remote.dto.company.CompanyDto
import com.alibbalci.isgmobil.domain.model.Company

fun CompanyDto.toDomain(): Company {
    return Company(
        id = id,
        name = name,
        address = address,
        hazardClass = hazardClass,
        phone = phone,
        occupationalPhysician = occupationalPhysician
    )
}