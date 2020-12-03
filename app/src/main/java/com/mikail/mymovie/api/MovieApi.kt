package com.mikail.mymovie.api


import com.mikail.mymovie.BuildConfig
import com.mikail.mymovie.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie")
    suspend fun getMovies(
        @Query("sort_by")sort_by:String = "popularity.desc",
        @Query("api_key") api_key:String = BuildConfig.API_KEY):Response<Movies>
}