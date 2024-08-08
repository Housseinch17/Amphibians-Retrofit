package com.example.amphibiansappretrofitdata.domain.usecase

import com.example.amphibiansappretrofitdata.data.datasource.AmphibiansRepositoryImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import javax.inject.Inject

class UpdateAmphibiansUseCase @Inject constructor(
    private val amphibiansRepositoryImpl: AmphibiansRepositoryImpl
) {
    suspend fun updateAmphibians(): List<AmphibiansItem>{
        return amphibiansRepositoryImpl.updateAmphibians()
    }
}