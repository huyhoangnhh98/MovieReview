package com.oxcoding.moviemvvm.data.vo


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetails(
    val budget: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Float
) : Serializable