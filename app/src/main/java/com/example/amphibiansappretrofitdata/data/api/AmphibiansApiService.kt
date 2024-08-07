package com.example.amphibiansappretrofitdata.data.api

import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import retrofit2.http.GET

interface AmphibiansApiService {

    @GET("amphibians")
   suspend fun getAmphibians(): List<AmphibiansItem>

}