package com.teacomputers.data.dataSource.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teacomputers.domain.entity.ResponseEntity

@Dao
interface TrendingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrending(responseEntity: ResponseEntity)

    @Query("SELECT * From responseEntity WHERE type = :type")
    fun getAllTrending(type: String): ResponseEntity?
}
