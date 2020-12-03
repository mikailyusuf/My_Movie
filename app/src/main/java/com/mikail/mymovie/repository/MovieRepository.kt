package com.mikail.mymovie.repository

import com.mikail.mymovie.api.ApiHelper
import com.mikail.mymovie.model.Result
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getMovies() = apiHelper.getMovies()


    suspend fun insert(movie: Result) = apiHelper.insert(movie)
    fun getMovieLocal() = apiHelper.getMovieLocal()
    suspend fun deleteMovie(movie: Result) = apiHelper.deleteMovie(movie)
}