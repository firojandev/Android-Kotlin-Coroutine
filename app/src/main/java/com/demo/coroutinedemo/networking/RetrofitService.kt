package com.demo.coroutinedemo.networking

import com.demo.coroutinedemo.movies.Movie
import com.demo.coroutinedemo.utils.AppsConstants
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(AppsConstants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }


    }
}