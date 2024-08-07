package com.example.amphibiansappretrofitdata.data.datasource.impl

import com.example.amphibiansappretrofitdata.data.api.AmphibiansApiService
import com.example.amphibiansappretrofitdata.data.datasource.datasource.NetworkAmphibians
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import javax.inject.Inject

class NetworkAmphibiansImpl @Inject constructor(
    private val amphibiansApiService: AmphibiansApiService
) : NetworkAmphibians {
    override suspend fun getAmphibians(): List<AmphibiansItem> {
        return amphibiansApiService.getAmphibians()
    }
}