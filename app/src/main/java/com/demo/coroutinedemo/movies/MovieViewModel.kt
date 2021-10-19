package com.demo.coroutinedemo.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MovieViewModel(private val movieRepository: MovieRepository):ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val movieList = MutableLiveData<List<Movie>>()

    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    val exceptionHandler = CoroutineExceptionHandler {_,throwable -> onError("Exception handled ${throwable.localizedMessage}")}
    private fun onError(msg:String){
        errorMessage.value = msg
        loading.value = false
    }

    fun getAllMovies(){
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = movieRepository.getAllMovies()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    movieList.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error:${response.message()}")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}