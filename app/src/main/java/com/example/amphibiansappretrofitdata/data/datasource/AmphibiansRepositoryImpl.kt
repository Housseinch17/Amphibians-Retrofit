package com.example.amphibiansappretrofitdata.data.datasource

import android.util.Log
import com.example.amphibiansappretrofitdata.data.datasource.impl.LocalAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.domain.repositories.AmphibiansRepository
import java.lang.Exception
import javax.inject.Inject

class AmphibiansRepositoryImpl @Inject constructor(
    private val networkAmphibiansImpl: NetworkAmphibiansImpl,
    private val localAmphibiansImpl: LocalAmphibiansImpl
) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<AmphibiansItem> {
        return getAmphibiansFromDb()
    }

    override suspend fun updateAmphibians(): List<AmphibiansItem> {
        val amphibiansList = getAmphibiansFromApi()
        localAmphibiansImpl.deleteAllAmphibians()
        localAmphibiansImpl.saveAmphibians(amphibiansList)
        return amphibiansList
    }

    private suspend fun getAmphibiansFromApi(): List<AmphibiansItem> {
        try {
            val amphibiansList = networkAmphibiansImpl.getAmphibians()
            return amphibiansList
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansFromApi() exception: "+exception.message.toString())
            return emptyList()
        }
    }

    private suspend fun getAmphibiansFromDb(): List<AmphibiansItem> {
        lateinit var amphibiansList: List<AmphibiansItem>
        try {
            amphibiansList = localAmphibiansImpl.getAmphibians()
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansFromDb() exception: "+exception.message.toString())
        }
        if (amphibiansList.isNotEmpty()) {
            return amphibiansList
        } else {
            amphibiansList = getAmphibiansFromApi()
            localAmphibiansImpl.saveAmphibians(amphibiansList)
        }
        return amphibiansList
    }
}

sealed interface NetworkCall {
    data class Success(val amphibiansList: List<AmphibiansItem>) : NetworkCall
    data object Error : NetworkCall
}