package com.example.moviereview.data.network.api



import com.example.moviereview.utils.Constrants.Companion.API_KEY
import com.oxcoding.moviemvvm.data.vo.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi{

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page : Int,
        @Query("api_key") api : String = API_KEY
    ) : Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopuparMovies(
        @Query("page") page : Int,
        @Query("api_key") api : String = API_KEY
    ) : Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page : Int,
        @Query("api_key") api : String = API_KEY
    ) : Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page : Int,
        @Query("api_key") api : String = API_KEY
    ) : Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id : Int,
        @Query("api_key") api: String = API_KEY
    )


}