package com.teacomputers.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseEntity(
    @field:SerializedName("page")
    val page: Int,
    @field:SerializedName("results")
    val results: ArrayList<ResultModel>,
    @field:SerializedName("total_pages")
    val total_pages: Int,
    @field:SerializedName("total_results")
    val total_results: Int
)

@Entity
data class ResultModel(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("id")
    var id: Int? = null,
    var type: String = "",
    @field:SerializedName("adult")
    var adult: Boolean = false,
    @field:SerializedName("backdrop_path")
    var backdrop_path: String = "",
    @field:SerializedName("original_language")
    var original_language: String = "",
    @field:SerializedName("original_title")
    var original_title: String = "",
    @field:SerializedName("overview")
    var overview: String = "",
    @field:SerializedName("popularity")
    var popularity: Double = 0.0,
    @field:SerializedName("poster_path")
    var poster_path: String = "",
    @field:SerializedName("release_date")
    var release_date: String = "",
    @field:SerializedName("title")
    var title: String = "",
    @field:SerializedName("name")
    var name: String = "",
    @field:SerializedName("video")
    var video: Boolean = false,
    @field:SerializedName("vote_average")
    var vote_average: Double = 0.0,
    @field:SerializedName("vote_count")
    var vote_count: Int = 0
)
