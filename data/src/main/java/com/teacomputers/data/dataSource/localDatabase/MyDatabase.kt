package com.teacomputers.data.dataSource.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teacomputers.data.dataSource.localDatabase.dao.TrendingDao
import com.teacomputers.domain.entity.ResultModel

@Database(entities = [ResultModel::class], version = 8, exportSchema = false)
@TypeConverters(Converter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao
}
