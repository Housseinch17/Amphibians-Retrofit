package com.example.amphibiansappretrofitdata.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem

@Database(
    entities = [AmphibiansItem::class],
    version = 1,
    exportSchema = false
)
abstract class AmphibiansDataBase : RoomDatabase() {
    abstract fun amphibiansDao(): AmphibiansDao
}