package com.demo.coroutinedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.coroutinedemo.databinding.ActivityMainBinding
import com.demo.coroutinedemo.movies.MovieRepository
import com.demo.coroutinedemo.movies.MovieViewModel
import com.demo.coroutinedemo.movies.MovieViewModelFactory
import com.demo.coroutinedemo.movies.MoviesRecyclerViewAdapter
import com.demo.coroutinedemo.networking.RetrofitService

class MainActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesRecyclerViewAdapter: MoviesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var retrofitService = RetrofitService.getInstance()
        val movieRepository = MovieRepository(retrofitService)
        moviesRecyclerViewAdapter = MoviesRecyclerViewAdapter()
        binding.recyclerview.adapter = moviesRecyclerViewAdapter

        movieViewModel = ViewModelProviders.of(this,MovieViewModelFactory(movieRepository)).get(MovieViewModel::class.java)


        movieViewModel.movieList.observe(this, Observer {
            moviesRecyclerViewAdapter.setMovies(it)
        })

        movieViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        movieViewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            }else{
                binding.progressDialog.visibility = View.GONE
            }

        })

        movieViewModel.getAllMovies()

    }
}