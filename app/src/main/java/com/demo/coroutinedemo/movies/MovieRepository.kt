package com.demo.coroutinedemo.movies

import com.demo.coroutinedemo.networking.RetrofitService

class MovieRepository(private val retrofitService: RetrofitService) {
    suspend fun getAllMovies()  = retrofitService.getAllMovies()
}