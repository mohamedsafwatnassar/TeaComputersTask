package com.teacomputers.domain.repo

import com.teacomputers.domain.dataHandler.DataState
import com.teacomputers.domain.entity.ResponseEntity

interface MovieRepo {
    suspend fun getTrendingMovie(): DataState<ResponseEntity?>
    suspend fun getSeries(): DataState<ResponseEntity?>
}
