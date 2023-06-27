package com.teacomputers.data.dataSource.localDatabase

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.teacomputers.domain.entity.ResultModel
import java.lang.reflect.Type

class Converter {

    @TypeConverter
    fun fromResultListToString(weather: List<ResultModel>): String? {
        val gson = Gson()
        return gson.toJson(weather)
    }

    @TypeConverter
    fun fromStringToResultList(string: String?): List<ResultModel> {
        val listType: Type = object : TypeToken<ResultModel>() {}.type
        return Gson().fromJson(string, listType)
    }
}