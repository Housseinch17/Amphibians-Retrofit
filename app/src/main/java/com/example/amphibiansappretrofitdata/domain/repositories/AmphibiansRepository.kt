package com.example.amphibiansappretrofitdata.domain.repositories

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansItem>
}