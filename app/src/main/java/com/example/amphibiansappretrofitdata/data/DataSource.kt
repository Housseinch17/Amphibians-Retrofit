package com.example.amphibiansappretrofitdata.data

import com.example.amphibiansappretrofitdata.R

object DataSource {
    const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    enum class AmphibianScreen(val title: Int) {
        Amphibians(R.string.amphibians),
        AmphibianDetail(R.string.amphibian_detail)
    }
}