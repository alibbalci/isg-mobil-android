package com.alibbalci.isgmobil.data.remote.dto.company

data class CompanyDto(
    val id: Long,
    val name: String,
    val address: String?,
    val hazardClass: String?,
    val phone: String?,
    val occupationalPhysician: String?,
    val createdAt: String?,
    val updatedAt: String?
)