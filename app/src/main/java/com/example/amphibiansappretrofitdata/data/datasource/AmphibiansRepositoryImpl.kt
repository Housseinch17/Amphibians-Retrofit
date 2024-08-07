package com.example.amphibiansappretrofitdata.data.datasource

import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.domain.repositories.AmphibiansRepository
import javax.inject.Inject

class AmphibiansRepositoryImpl @Inject constructor(
    private val networkAmphibiansImpl: NetworkAmphibiansImpl
): AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansItem> {
        return networkAmphibiansImpl.getAmphibians()
    }
}