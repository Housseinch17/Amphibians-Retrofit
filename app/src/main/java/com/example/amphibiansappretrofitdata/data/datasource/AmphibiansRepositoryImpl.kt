package com.example.amphibiansappretrofitdata.data.datasource

import android.util.Log
import com.example.amphibiansappretrofitdata.data.datasource.impl.LocalAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.domain.repositories.AmphibiansRepository
import retrofit2.HttpException
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

    override suspend fun getAmphibiansByName(name: String): AmphibiansItem? {
        var amphibian: AmphibiansItem? = null
        try {
            amphibian = localAmphibiansImpl.getAmphibiansByName(name)
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansByName() exception: " + exception.message.toString())
        } catch (e: HttpException) {
            Log.i("MyTag", "getAmphibiansByName() exception: " + e.message.toString())
        }
        return amphibian
    }

    private suspend fun getAmphibiansFromApi(): List<AmphibiansItem> {
        var amphibiansList = emptyList<AmphibiansItem>()
        try {
            amphibiansList = networkAmphibiansImpl.getAmphibians()
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansFromApi() exception: " + exception.message.toString())
        } catch (e: HttpException) {
            Log.i("MyTag", "getAmphibiansFromApi() exception: " + e.message.toString())
        }
        return amphibiansList
    }

    private suspend fun getAmphibiansFromDb(): List<AmphibiansItem> {
        var amphibiansList = emptyList<AmphibiansItem>()
        try {
            amphibiansList = localAmphibiansImpl.getAmphibians()
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansFromDb() exception: " + exception.message.toString())
        }
        if (amphibiansList.isEmpty()) {
            amphibiansList = getAmphibiansFromApi()
            localAmphibiansImpl.saveAmphibians(amphibiansList)
        }
        return amphibiansList
    }
}