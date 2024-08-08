package com.example.amphibiansappretrofitdata.data.di

import com.example.amphibiansappretrofitdata.data.datasource.AmphibiansRepositoryImpl
import com.example.amphibiansappretrofitdata.data.datasource.impl.LocalAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import com.example.amphibiansappretrofitdata.domain.repositories.AmphibiansRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AmphibiansRepositoryDI {

    @Singleton
    @Provides
    fun provideAmphibiansRepository(networkAmphibiansImpl: NetworkAmphibiansImpl,localAmphibiansImpl: LocalAmphibiansImpl): AmphibiansRepository{
        return AmphibiansRepositoryImpl(networkAmphibiansImpl,localAmphibiansImpl)
    }
}