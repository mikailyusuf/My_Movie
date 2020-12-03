package com.mikail.mymovie.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikail.mymovie.api.ApiHelper
import com.mikail.mymovie.model.Movies
import com.mikail.mymovie.model.Result
import com.mikail.mymovie.repository.MovieRepository
import com.mikail.mymovie.utils.NetWorkHelper
import com.mikail.mymovie.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

//Movies View Model

class MovieViewModel  @ViewModelInject constructor(
        private val repository: MovieRepository,
        private val networkHelper: NetWorkHelper
) : ViewModel() {

    val movies: MutableLiveData<Resource<Movies>> = MutableLiveData()


    private suspend fun get_movie_from_api() {
        movies.postValue(Resource.Loading())
        try {
            if (networkHelper.isNetworkConnected()) {
                val response = repository.getMovies()

                if (response.isSuccessful) {
                    response.body()?.let { result ->

                        movies.postValue(Resource.Success(result))

                    }
                }
                else{
                    movies.postValue( Resource.Error(response.message()))

                }

            } else {
                movies.postValue(Resource.Error("No Internet Connection"))
            }

        } catch (t: Throwable) {
            when (t) {
                is IOException -> movies.postValue(Resource.Error("Network Error"))
                else -> movies.postValue(Resource.Error("Error ${t.message}"))
            }
        }

    }

    fun getMoview() = viewModelScope.launch {
        get_movie_from_api()
    }


    fun saveMovie(movie: Result) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun deleteMovie(movie: Result) = viewModelScope.launch {

        repository.deleteMovie(movie)
    }

    fun getMovieLocal() = repository.getMovieLocal()

}