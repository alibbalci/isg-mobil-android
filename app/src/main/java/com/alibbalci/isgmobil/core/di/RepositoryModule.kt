package com.alibbalci.isgmobil.core.di

import com.alibbalci.isgmobil.data.repository.AuthRepositoryImpl
import com.alibbalci.isgmobil.data.repository.CompanyRepositoryImpl
import com.alibbalci.isgmobil.data.repository.ObservationRepositoryImpl
import com.alibbalci.isgmobil.domain.repository.AuthRepository
import com.alibbalci.isgmobil.domain.repository.CompanyRepository
import com.alibbalci.isgmobil.domain.repository.ObservationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindCompanyRepository(
        impl: CompanyRepositoryImpl
    ): CompanyRepository

    @Binds
    @Singleton
    abstract fun bindObservationRepository(
        impl: ObservationRepositoryImpl
    ): ObservationRepository
}