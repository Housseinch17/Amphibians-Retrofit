package com.example.amphibiansappretrofitdata.domain.repositories

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansItem>
    suspend fun updateAmphibians(): List<AmphibiansItem>
    suspend fun getAmphibiansByName(name: String): AmphibiansItem?
}