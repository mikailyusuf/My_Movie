package com.mikail.mymovie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.Serializable

@Entity(
    tableName = "movies"

)
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
): Serializable


class Converters {

    @TypeConverter
    fun fromList( genre_ids: List<Int>):String{
        return  genre_ids.toString()
    }

    @TypeConverter
    fun toList(genre_ids:String): List<Int> {

        val data:List<Int> = listOf(1,2)
        return data
    }
}