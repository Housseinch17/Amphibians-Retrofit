package com.example.amphibiansappretrofitdata.data.datasource.datasource

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

interface NetworkAmphibians{
    suspend fun getAmphibians(): List<AmphibiansItem>
}