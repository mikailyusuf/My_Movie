package com.mikail.mymovie.localdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.mikail.mymovie.model.Result


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Result):Long

    @Query("SELECT * FROM movies")
    fun getMoviesLocal():LiveData<List<Result>>

    @Delete
    suspend fun deleteMovie(movie: Result)
}