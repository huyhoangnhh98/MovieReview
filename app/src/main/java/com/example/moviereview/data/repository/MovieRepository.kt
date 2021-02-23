package com.example.moviereview.data.repository

import com.example.moviereview.data.network.api.RetrofitInstance

class MovieRepository {


    suspend fun getNowPlaying(page : Int) = RetrofitInstance.api.getNowPlayingMovies(page)

    suspend fun getPopular(page: Int) = RetrofitInstance.api.getPopuparMovies(page)

    suspend fun getTopRate(page: Int) = RetrofitInstance.api.getTopRatedMovies(page)

    suspend fun getUpComming(page: Int) = RetrofitInstance.api.getUpComingMovies(page)

    suspend fun getMovieDetail(id : Int) = RetrofitInstance.api.getMovieDetails(id)

}