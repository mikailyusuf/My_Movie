package com.mikail.mymovie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikail.mymovie.R
import com.mikail.mymovie.adapter.MovieRecyclerAdapter
import com.mikail.mymovie.model.Result
import com.mikail.mymovie.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*

@AndroidEntryPoint
class FavouriteMovies : Fragment(R.layout.fragment_favourite_movies) {

    private lateinit var movieAdapter: MovieRecyclerAdapter
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        var movies: List<Result> = listOf()
        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movies", it)
            }

            findNavController().navigate(R.id.action_favouriteMovies_to_movieDetailsActivity, bundle)
        }

//        LifeCycle Observer to get Movies from local Db
        movieViewModel.getMovieLocal().observe(viewLifecycleOwner, Observer {

            if (it != null) {
                movies =it
                movieAdapter.differ.submitList(movies)
            }

        })
    }


    private fun setupRecycler() {
        movieAdapter = MovieRecyclerAdapter()
        recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}