package com.mikail.mymovie.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikail.mymovie.localdb.MovieDao
import com.mikail.mymovie.model.Movies
import com.mikail.mymovie.model.Result
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val api: MovieApi, val db: MovieDao) : ApiHelper {
    override suspend fun getMovies(): Response<Movies> {
         return  api.getMovies()
    }

    override suspend fun insert(movie: Result): Long = db.insert(movie)

    override fun getMovieLocal(): LiveData<List<Result>> = db.getMoviesLocal()

    override suspend fun deleteMovie(movie: Result) =db.deleteMovie(movie)


}