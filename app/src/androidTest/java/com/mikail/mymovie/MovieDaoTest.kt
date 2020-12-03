package com.mikail.mymovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mikail.mymovie.localdb.MovieDao
import com.mikail.mymovie.localdb.MovieDb
import com.mikail.mymovie.model.Result
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//This Class Test The Movie ViewModel For all Use Cases


@RunWith(AndroidJUnit4::class)
@SmallTest
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MovieDb
    private lateinit var dao: MovieDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDb::class.java
        ).allowMainThreadQueries().build()
        dao = database.getMovie()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun insertMovie() = runBlockingTest {
        val genres = listOf<Int>(1,2)
        val movie = Result(false, "/fTDzKoQIh1HeyjfpG5AHMi2jxAJ.jpg",genres,682377, "en","Chick Fight",
            "When Anna Wyncomb is introduced to an an underground, all-female fight club in order to turn the mess of her life around, she discovers she is much more personally connected to the history of the club than she could ever imagine.",
            1443.813,"/4ZocdxnOO6q2UbdKye2wgofLFhB.jpg","2020-11-13", "Chick Fight", false, 5.9, 41
            )
        dao.insert(movie)

        val allMovies = dao.getMoviesLocal().getOrAwaitValue()

        assertThat(allMovies).contains(movie)
    }

    @Test
    fun deleteMovie() = runBlockingTest {
        val genres = listOf<Int>(1,2)
        val movie = Result(false, "/fTDzKoQIh1HeyjfpG5AHMi2jxAJ.jpg",genres,682377, "en","Chick Fight",
            "When Anna Wyncomb is introduced to an an underground, all-female fight club in order to turn the mess of her life around, she discovers she is much more personally connected to the history of the club than she could ever imagine.",
            1443.813,"/4ZocdxnOO6q2UbdKye2wgofLFhB.jpg","2020-11-13", "Chick Fight", false, 5.9, 41
        )
        dao.deleteMocvie(movie)
        val allMovies = dao.getMoviesLocal().getOrAwaitValue()
            assertThat(allMovies).doesNotContain(movie)


    }

}