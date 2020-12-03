package com.mikail.mymovie.di

import android.content.Context
import androidx.room.Room
import com.mikail.mymovie.api.ApiHelper
import com.mikail.mymovie.api.ApiHelperImpl
import com.mikail.mymovie.api.MovieApi
import com.mikail.mymovie.localdb.MovieDb
import com.mikail.mymovie.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provieMovieDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        MovieDb::class.java,
        "movie_db.db"
    ).build()

    @Provides
    @Singleton
    fun provideArticleDAO(movieDb: MovieDb) = movieDb.getMovie()

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @Provides
    @Singleton
    fun provideRetrofit(): MovieApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }




}