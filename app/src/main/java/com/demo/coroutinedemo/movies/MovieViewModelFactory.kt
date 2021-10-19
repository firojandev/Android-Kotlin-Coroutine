package com.demo.coroutinedemo.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MovieViewModelFactory (private val movieRepository: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(movieRepository) as T
        }else{
            throw IllegalArgumentException("Model class not found")
        }
    }


}