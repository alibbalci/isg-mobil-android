package com.alibbalci.isgmobil.domain.model


data class Company(
    val id: Long,
    val name: String,
    val address: String?,
    val hazardClass: String?,
    val phone: String?,
    val occupationalPhysician: String?
)