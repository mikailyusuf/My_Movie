package com.mikail.mymovie.ui

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.mikail.mymovie.R
import com.mikail.mymovie.model.Result
import com.mikail.mymovie.viewModel.MovieViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*

//Movies Model is Passed From RecyclerView Item to The Deatils Activity Using Navigation Component

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private val args: MovieDetailsActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movies = args.movies
        var localMovies: List<Result> = listOf()

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val title = findViewById<TextView>(R.id.title)
        val release_date = findViewById<TextView>(R.id.release_date)
        val overview = findViewById<TextView>(R.id.overview)
        val vote_average = findViewById<TextView>(R.id.vote_average)

        save_btn.text = " is Saved"

//        Live Data Observer For Getting Saved Movies From Room DataBase

        movieViewModel.getMovieLocal().observe(this, Observer {

            if (it != null) {
                localMovies = it

                if (checkMovie(movies!!, localMovies)) {
                    save_btn.text = "${movies.title} is Saved"

                }

                save_btn.text = "Save ${movies.title} to Favourites"

            }


        })


        if (movies != null) {
            title.text = movies.title
            release_date.text = movies.release_date
            overview.text = movies.overview
            vote_average.text = movies.vote_average.toString()
            toolbar.title = movies.title

            val image_path = "http://image.tmdb.org/t/p/original/${movies.poster_path}"
            Picasso.get()
                .load(image_path)
                .into(imageView)

        }



        save_btn.setOnClickListener {

            if (movies != null) {
                if (checkMovie(movies, localMovies)) {
                    Snackbar.make(it, "${movies.title} is already Saved to Favourites", Snackbar.LENGTH_LONG)
                        .setAction("DELETE") {
                            if (movies != null) {
                                movieViewModel.deleteMovie(movies)
                            }

                        }
                        .show()
                    save_btn.text = "${movies.title} is Saved"

                } else {

                    movieViewModel.saveMovie(movies)
                    Snackbar.make(it, "${movies.title} is saved to Favourites", Snackbar.LENGTH_SHORT)
                        .setAction("Undo") {
                            if (movies != null) {
                                movieViewModel.deleteMovie(movies)
                            }

                        }.show()
                    save_btn.text = "${movies.title} is Saved"

                }


            }


        }
    }

    //Function to check if movie is saved to local Db
    fun checkMovie(movie: Result, savedMovies: List<Result>): Boolean {
        var b = false
        for (i in savedMovies.indices) {
            if (movie.id == savedMovies[i].id) {
               b = true
                save_btn.text = "${movie.title} is Saved"

            }



        }
        return b
    }


}
