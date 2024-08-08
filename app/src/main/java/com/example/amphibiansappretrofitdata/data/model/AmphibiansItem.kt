package com.example.amphibiansappretrofitdata.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Entity(tableName = "amphibians_item")
@Serializable
data class AmphibiansItem(
    val description: String,
    @SerialName("img_src")
    val image: String,
    @PrimaryKey
    val name: String,
    val type: String
)