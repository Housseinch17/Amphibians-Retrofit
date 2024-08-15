package com.example.amphibiansappretrofitdata.data.datasource.impl

import com.example.amphibiansappretrofitdata.data.datasource.datasource.LocalAmphibians
import com.example.amphibiansappretrofitdata.data.db.AmphibiansDao
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import javax.inject.Inject

class LocalAmphibiansImpl @Inject constructor(
    private val amphibiansDao: AmphibiansDao
): LocalAmphibians{
    override suspend fun saveAmphibians(amphibians: List<AmphibiansItem>) {
        return amphibiansDao.saveAmphibians(amphibians)
    }

    override suspend fun getAmphibians(): List<AmphibiansItem> {
        return amphibiansDao.getAmphibians()
    }

    override suspend fun deleteAllAmphibians() {
        return amphibiansDao.deleteAllAmphibians()
    }

    override suspend fun getAmphibiansByName(name: String): AmphibiansItem? {
        return amphibiansDao.getAmphibianByName(name)
    }
}