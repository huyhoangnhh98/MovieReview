package com.example.moviereview.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviereview.data.repository.MovieRepository

class MovieViewModelFactory(
    val movieRepository: MovieRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieRepository) as T
    }
}