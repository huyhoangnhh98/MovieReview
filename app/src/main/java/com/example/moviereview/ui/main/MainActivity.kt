package com.example.moviereview.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviereview.R
import com.example.moviereview.data.repository.MovieRepository
import com.example.moviereview.databinding.ActivityMainBinding
import com.example.moviereview.ui.movie.MovieViewModel
import com.example.moviereview.ui.movie.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        val newsrepository = MovieRepository()
        val viewModelProviderFactory = MovieViewModelFactory(newsrepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)

        binding.bottomNavigation.setupWithNavController(fragment.findNavController())
        //bottom_navigation.setupWithNavController(fragment.findNavController())
    }


}