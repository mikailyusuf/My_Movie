package com.mikail.mymovie.api

import androidx.lifecycle.LiveData
import com.mikail.mymovie.model.Movies
import com.mikail.mymovie.model.Result
import retrofit2.Response


interface ApiHelper {
    suspend fun getMovies(): Response<Movies>


    suspend fun insert(movies: Result):Long
    fun getMovieLocal(): LiveData<List<Result>>
    suspend fun deleteMovie(movie: Result)
}