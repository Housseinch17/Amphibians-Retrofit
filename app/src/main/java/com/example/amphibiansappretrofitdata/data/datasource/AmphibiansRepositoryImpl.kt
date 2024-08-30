package com.example.amphibiansappretrofitdata.data.datasource

import android.util.Log
import com.example.amphibiansappretrofitdata.data.datasource.impl.LocalAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.datasource.impl.NetworkAmphibiansImpl
import com.example.amphibiansappretrofitdata.data.model.AmphibiansItem
import com.example.amphibiansappretrofitdata.domain.repositories.AmphibiansRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class AmphibiansRepositoryImpl @Inject constructor(
    private val networkAmphibiansImpl: NetworkAmphibiansImpl,
    private val localAmphibiansImpl: LocalAmphibiansImpl,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<AmphibiansItem> = withContext(ioDispatcher) {
        return@withContext getAmphibiansFromDb()
    }

    override suspend fun updateAmphibians(): List<AmphibiansItem> = withContext(ioDispatcher) {
        val amphibiansList: List<AmphibiansItem> = getAmphibiansFromApi()
        if (amphibiansList.isEmpty()) {
            return@withContext getAmphibiansFromDb()
        }
        localAmphibiansImpl.deleteAllAmphibians()
        localAmphibiansImpl.saveAmphibians(amphibiansList)
        return@withContext amphibiansList
    }

    override suspend fun getAmphibiansByName(name: String): AmphibiansItem? =
        withContext(ioDispatcher) {
            var amphibian: AmphibiansItem? = null
            try {
                amphibian = localAmphibiansImpl.getAmphibiansByName(name)
            } catch (exception: Exception) {
                Log.i("MyTag", "getAmphibiansByName() exception: " + exception.message.toString())
            } catch (e: HttpException) {
                Log.i("MyTag", "getAmphibiansByName() exception: " + e.message.toString())
            }
            return@withContext amphibian
        }

    private suspend fun getAmphibiansFromApi(): List<AmphibiansItem> = withContext(ioDispatcher) {
        var amphibiansList = emptyList<AmphibiansItem>()
        try {
            amphibiansList = networkAmphibiansImpl.getAmphibians()
        } catch (exception: Exception) {
            Log.i("MyTag", "getAmphibiansFromApi() exception: " + exception.message.toString())
        } catch (e: HttpException) {
            Log.i("MyTag", "getAmphibiansFromApi() exception: " + e.message.toString())
        }
        return@withContext amphibiansList
    }

    private suspend fun getAmphibiansFromDb(): List<AmphibiansItem> = withContext(ioDispatcher) {
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
        return@withContext amphibiansList
    }
}