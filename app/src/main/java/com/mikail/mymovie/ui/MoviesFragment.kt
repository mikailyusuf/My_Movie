package com.mikail.mymovie.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikail.mymovie.R
import com.mikail.mymovie.adapter.MovieRecyclerAdapter
import com.mikail.mymovie.model.Result
import com.mikail.mymovie.utils.Resource
import com.mikail.mymovie.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*


@AndroidEntryPoint
class MoviesFragment : Fragment(R.layout.fragment_movies) {
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieRecyclerAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        var movies: List<Result> = listOf()

        movieAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movies", it)
            }

            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsActivity, bundle)
        }

        movieViewModel.getMoview()

        //Lifecycle Observer to get Movies from the Api
        movieViewModel.movies.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Loading -> {

                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    response?.data.let {

                        if (it != null) {
                            movies = it.results
                            movieAdapter.differ.submitList(movies)
                        }
                    }

                }


                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE

                    response.message?.let {
                        Toast.makeText(activity, "An Error Occured Please try again later", Toast.LENGTH_SHORT)
                            .show()
                    }
                }


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