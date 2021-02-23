package com.example.moviereview.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.moviereview.R
import com.example.moviereview.databinding.FragmentMovieDetailsBinding
import com.example.moviereview.ui.main.MainActivity

const val MOVIE_BACKDROP = "extra_movie_backdrop"
const val MOVIE_POSTER = "extra_movie_poster"
const val MOVIE_TITLE = "extra_movie_title"
const val MOVIE_RATING = "extra_movie_rating"
const val MOVIE_RELEASE_DATE = "extra_movie_release_date"
const val MOVIE_OVERVIEW = "extra_movie_overview"

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding : FragmentMovieDetailsBinding

    val args : MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)

        viewModel = (activity as MainActivity).viewModel

        val movie = args.movie

        binding.movieTitle.text = movie.title

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
            .transform(CenterCrop())
            .into(binding.moviePoster)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280${movie.backdropPath}")
            .transform(CenterCrop())
            .into(binding.movieBackdrop)


        binding.movieReleaseDate.text = movie.releaseDate
        binding.movieOverview.text = movie.overview
        binding.movieRating.rating = movie.rating/2


        return  binding.root
    }



}