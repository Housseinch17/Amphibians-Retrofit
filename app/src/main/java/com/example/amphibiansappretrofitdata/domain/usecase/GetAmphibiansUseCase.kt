package com.example.amphibiansappretrofitdata.domain.usecase

import com.example.amphibiansappretrofitdata.data.datasource.AmphibiansRepositoryImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import javax.inject.Inject

class GetAmphibiansUseCase @Inject constructor(
    private val amphibiansRepositoryImpl: AmphibiansRepositoryImpl
) {
    suspend fun getAmphibians(): List<AmphibiansItem>{
        return amphibiansRepositoryImpl.getAmphibians()
    }
}