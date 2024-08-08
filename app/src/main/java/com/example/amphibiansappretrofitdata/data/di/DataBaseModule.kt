package com.example.amphibiansappretrofitdata.data.di

import android.app.Application
import androidx.room.Room
import com.example.amphibiansappretrofitdata.data.db.AmphibiansDao
import com.example.amphibiansappretrofitdata.data.db.AmphibiansDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideAmphibiansDataBase(app: Application): AmphibiansDataBase{
        return Room.databaseBuilder(app,AmphibiansDataBase::class.java,"amphibiansclient")
            .build()
    }
    @Singleton
    @Provides
    fun provideAmphibiansDao(amphibiansDataBase: AmphibiansDataBase): AmphibiansDao{
        return amphibiansDataBase.amphibiansDao()
    }


}