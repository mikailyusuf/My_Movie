package com.mikail.mymovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikail.mymovie.R
import com.mikail.mymovie.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_layout.view.*


class MovieRecyclerAdapter: RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {


    inner class  MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    private val differCallback =  object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return  oldItem == newItem

        }
    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerAdapter.MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size

    }

    override fun onBindViewHolder(holder: MovieRecyclerAdapter.MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {
            title.text = movie.title
            val image_path = "http://image.tmdb.org/t/p/original/${movie.poster_path}"
            Picasso.get()
                .load(image_path)
                .into(image)

            setOnClickListener{
                onItemClickListener?.let {it(movie)

                }
            }

        }
    }

    private var onItemClickListener:((Result)-> Unit)?= null
    fun setOnItemClickListener(listener:(Result) -> Unit){
        onItemClickListener  = listener
    }

}