package com.example.amphibiansappretrofitdata.data.di

import android.net.Network
import com.example.amphibiansappretrofitdata.data.api.AmphibiansApiService
import com.example.amphibiansappretrofitdata.data.datasource.datasource.NetworkAmphibians
import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkAmphibiansDI {

    @Singleton
    @Provides
    fun provideNetworkAmphibians(amphibiansApiService: AmphibiansApiService): NetworkAmphibians{
        return NetworkAmphibiansImpl(amphibiansApiService)
    }
}