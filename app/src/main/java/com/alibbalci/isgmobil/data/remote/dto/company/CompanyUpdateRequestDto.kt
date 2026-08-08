package com.alibbalci.isgmobil.data.remote.dto.company

data class CompanyUpdateRequestDto(
    val name: String,
    val address: String?,
    val hazardClass: String?,
    val phone: String?,
    val occupationalPhysician: String?
)