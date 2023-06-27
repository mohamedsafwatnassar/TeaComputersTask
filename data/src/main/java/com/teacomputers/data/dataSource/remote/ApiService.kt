package com.teacomputers.data.dataSource.remote

import com.teacomputers.domain.entity.ResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getTrendingMovie(
        @Query("api_key") apiKey: String?
    ): Response<ResponseEntity>

    @GET("tv/popular")
    suspend fun getSeries(
        @Query("api_key") apiKey: String?
    ): Response<ResponseEntity>
}
