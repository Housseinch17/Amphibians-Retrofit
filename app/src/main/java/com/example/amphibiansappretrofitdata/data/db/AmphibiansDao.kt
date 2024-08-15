package com.example.amphibiansappretrofitdata.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

@Dao
interface AmphibiansDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAmphibians(amphibians: List<AmphibiansItem>)

    @Query("DELETE FROM amphibians_item")
    suspend fun deleteAllAmphibians()

    @Query("SELECT * FROM amphibians_item")
    suspend fun getAmphibians(): List<AmphibiansItem>

    @Query("SELECT * FROM amphibians_item WHERE name = :name LIMIT 1")
    suspend fun getAmphibianByName(name: String?): AmphibiansItem?
}