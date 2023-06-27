package com.teacomputers.moviesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teacomputers.domain.dataHandler.DataState
import com.teacomputers.domain.entity.ResponseEntity
import com.teacomputers.domain.entity.ResultModel
import com.teacomputers.domain.usecases.GetTrendingSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val getAllTrendingUseCas: GetTrendingSeriesUseCase
) : ViewModel() {
    var selectedTrending = ResultModel()

    private val _trendingData: MutableLiveData<DataState<ResponseEntity?>?> = MutableLiveData()
    val liveTrendingData: LiveData<DataState<ResponseEntity?>?> = _trendingData

    fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                _trendingData.value = getAllTrendingUseCas()
            } catch (exception: Exception) {
                Log.d("getTrendingViewModel", exception.message.toString())
                _trendingData.value = DataState.ErrorMessage(exception.message.toString())
            }
        }
    }

    fun getSeries() {
        viewModelScope.launch {
            try {
                _trendingData.value = getAllTrendingUseCas()
            } catch (exception: Exception) {
                Log.d("getSeriesViewModel", exception.message.toString())
                _trendingData.value = DataState.ErrorMessage(exception.message.toString())
            }
        }
    }
}
