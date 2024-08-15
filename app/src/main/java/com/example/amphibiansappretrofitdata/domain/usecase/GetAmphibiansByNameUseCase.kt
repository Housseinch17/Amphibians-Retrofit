package com.example.amphibiansappretrofitdata.domain.usecase

import com.example.amphibiansappretrofitdata.data.datasource.AmphibiansRepositoryImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import javax.inject.Inject

class GetAmphibiansByNameUseCase @Inject constructor(
    private val repositoryImpl: AmphibiansRepositoryImpl
) {
    suspend fun getAmphibiansByName(name: String): AmphibiansItem?{
        return repositoryImpl.getAmphibiansByName(name)
    }
}