package com.example.moviereview.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviereview.data.repository.MovieRepository
import com.example.moviereview.utils.Resource
import com.oxcoding.moviemvvm.data.vo.MovieResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(
    val movieRepository : MovieRepository
): ViewModel() {

    val nowPlayingMovie : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var nowPlayingPage = 1
    var nowPlayingResponse : MovieResponse? = null

    val popularMovie : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var popularPage = 1
    var popularResponse : MovieResponse? = null

    val topRateMovie : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var topRatePage = 1
    var topRateResponse : MovieResponse? = null

    val upComingMovie : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    var upComingPage = 1
    var upComingResponse : MovieResponse? = null

    init {
        getNowPlaying()
        getPopular()
        getTopRate()
        getUpComing()
    }

    fun getNowPlaying() = viewModelScope.launch {
        nowPlayingMovie.postValue(Resource.Loading())
        val response = movieRepository.getNowPlaying(nowPlayingPage)
        nowPlayingMovie.postValue(handleNowPlayingResponse(response))
    }

    fun handleNowPlayingResponse( response: Response<MovieResponse>) : Resource<MovieResponse>{
//        if(response.isSuccessful){
//            response.body()?.let { resultResponse ->
//                return Resource.Success(resultResponse)
//            }
//        }
//        return Resource.Error(response.message())
        if(response.isSuccessful){
            response.body()?.let { resultRespone ->
                nowPlayingPage++
                if(nowPlayingResponse == null){
                    nowPlayingResponse = resultRespone
                } else {
                    val oldMovies = nowPlayingResponse?.movieList
                    val newMovies = resultRespone.movieList
                    oldMovies?.addAll(newMovies)
                }
                return Resource.Success(nowPlayingResponse?: resultRespone)
            }

        }
        return Resource.Error(response.message())
    }

    fun getPopular() = viewModelScope.launch {
        popularMovie.postValue(Resource.Loading())
        val response = movieRepository.getPopular(popularPage)
        popularMovie.postValue(handlePopularResponse(response))
    }

    fun handlePopularResponse( response: Response<MovieResponse>) : Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultRespone ->
                popularPage++
                if(popularResponse == null){
                    popularResponse = resultRespone
                } else {
                    val oldMovies = popularResponse?.movieList
                    val newMovies = resultRespone.movieList
                    oldMovies?.addAll(newMovies)
                }
                return Resource.Success(popularResponse?: resultRespone)
            }

        }
        return Resource.Error(response.message())
    }

    fun getTopRate() = viewModelScope.launch {
        topRateMovie.postValue(Resource.Loading())
        val response = movieRepository.getTopRate(topRatePage)
        topRateMovie.postValue(handleTopRateResponse(response))
    }

    fun handleTopRateResponse(response: Response<MovieResponse>) : Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultRespone ->
                topRatePage++
                if(topRateResponse == null){
                    topRateResponse = resultRespone
                } else {
                    val oldMovies = topRateResponse?.movieList
                    val newMoviews = resultRespone.movieList
                    oldMovies?.addAll(newMoviews)
                }
                return  Resource.Success(topRateResponse?:resultRespone)
            }
        }
        return Resource.Error(response.message())
    }

    fun getUpComing() = viewModelScope.launch {
        upComingMovie.postValue(Resource.Loading())
        val response = movieRepository.getUpComming(upComingPage)
        upComingMovie.postValue(handleUpComingResponse(response))
    }

    fun handleUpComingResponse(response: Response<MovieResponse>) : Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let { resultRespone ->
                upComingPage++
                if(upComingResponse == null){
                    upComingResponse = resultRespone
                } else {
                    val oldMovies = upComingResponse?.movieList
                    val newMoviews = resultRespone.movieList
                    oldMovies?.addAll(newMoviews)
                }
                return  Resource.Success(upComingResponse?:resultRespone)
            }
        }
        return Resource.Error(response.message())
    }


}