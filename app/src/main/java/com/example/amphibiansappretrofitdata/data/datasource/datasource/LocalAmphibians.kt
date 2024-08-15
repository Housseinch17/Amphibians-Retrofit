package com.example.amphibiansappretrofitdata.data.datasource.datasource

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

interface LocalAmphibians {
    suspend fun saveAmphibians(amphibians: List<AmphibiansItem>)
    suspend fun getAmphibians(): List<AmphibiansItem>
    suspend fun deleteAllAmphibians()
    suspend fun getAmphibiansByName(name: String): AmphibiansItem?
}