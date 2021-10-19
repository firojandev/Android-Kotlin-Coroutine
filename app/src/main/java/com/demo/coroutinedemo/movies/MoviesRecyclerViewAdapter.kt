package com.demo.coroutinedemo.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.coroutinedemo.databinding.MovieItemRowBinding

class MoviesRecyclerViewAdapter:RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    var moviesList = mutableListOf<Movie>()

    fun setMovies(movies: List<Movie>){
       this.moviesList.clear()
       this.moviesList = movies.toMutableList()
       notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemRowBinding.inflate(inflater,parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = moviesList[position]
        holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class MovieViewHolder(val binding:MovieItemRowBinding):RecyclerView.ViewHolder(binding.root){

    }
}