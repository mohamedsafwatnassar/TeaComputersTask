package com.teacomputers.data.repo

import com.teacomputers.data.BuildConfig
import com.teacomputers.data.dataSource.remote.ApiService
import com.teacomputers.data.dataSource.localDatabase.dao.TrendingDao
import com.teacomputers.domain.dataHandler.DataState
import com.teacomputers.domain.entity.ResponseEntity
import com.teacomputers.domain.repo.MovieRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieRepoImpl(
    private val apiService: ApiService,
    private val trendingDao: TrendingDao
) : MovieRepo {

    override suspend fun getTrendingMovie(): DataState<ResponseEntity?> {
        return try {
            val response = apiService.getTrendingMovie(BuildConfig.apiKey)
            if (response.code() == 200 || response.code() == 201) {
                // Store data offline
                // saveTrendingMovie(setItemType(response.body()!!, "Movie"))
                withContext(Dispatchers.Main) {
                    DataState.Success(response.body())
                }
            } else {
                DataState.ErrorMessage("adaklf")
            }
        } catch (ex: Exception) {
            return DataState.ErrorMessage(ex.localizedMessage?.toString() ?: "")
        }
    }

    override suspend fun getSeries(): DataState<ResponseEntity?> {
        return try {
            val response = apiService.getSeries(BuildConfig.apiKey)
            if (response.code() == 200 || response.code() == 201) {
                // Store data offline
                // saveTrendingMovie(setItemType(response.body()!!, "Series"))
                withContext(Dispatchers.Main) {
                    DataState.Success(response.body())
                }
            } else {
                DataState.ErrorMessage("adaklf")
            }
        } catch (ex: Exception) {
            return DataState.ErrorMessage("adaklf")
        }
    }

    private fun saveTrendingMovie(item: ResponseEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                trendingDao.insertTrending(item)
            } catch (ex: Exception) {
                DataState.ErrorMessage(ex.localizedMessage?.toString() ?: "")
            }
        }
    }

    private fun setItemType(item: ResponseEntity, type: String): ResponseEntity {
        item.results.forEach { it.type = type }
        return item
    }
}
