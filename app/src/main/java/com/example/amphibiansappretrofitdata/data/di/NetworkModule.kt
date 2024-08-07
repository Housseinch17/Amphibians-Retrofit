package com.example.amphibiansappretrofitdata.data.di

import com.example.amphibiansappretrofitdata.data.DataSource.BASE_URL
import com.example.amphibiansappretrofitdata.data.api.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //with serialization
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideMarsApiService(retrofit: Retrofit): AmphibiansApiService{
        return retrofit.create(AmphibiansApiService::class.java)
    }
}