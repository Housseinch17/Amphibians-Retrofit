package com.example.amphibiansappretrofitdata.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansItem(
    val description: String,
    @SerialName("img_src")
    val image: String,
    val name: String,
    val type: String
)