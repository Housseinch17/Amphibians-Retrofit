package com.example.amphibiansappretrofitdata.data.di

import com.example.amphibiansappretrofitdata.data.datasource.datasource.LocalAmphibians
import com.example.amphibiansappretrofitdata.data.datasource.impl.LocalAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.db.AmphibiansDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalAmphibiansDI {

    @Singleton
    @Provides
    fun provideLocalAmphibians(amphibiansDao: AmphibiansDao): LocalAmphibians{
        return LocalAmphibiansImpl(amphibiansDao)
    }
}