package com.example.moviereview.ui.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereview.R
import com.example.moviereview.databinding.MovieFragmentBinding
import com.example.moviereview.ui.adapter.MoviesAdapter
import com.example.moviereview.ui.main.MainActivity
import com.example.moviereview.utils.Resource
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment : Fragment(R.layout.movie_fragment) {
    private lateinit var binding :  MovieFragmentBinding

    private lateinit var nowPlayingAdapter: MoviesAdapter
    private lateinit var popularAdapter: MoviesAdapter
    private lateinit var topRateAdapter: MoviesAdapter
    private lateinit var upComingAdapter: MoviesAdapter

    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_fragment, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = (activity as MainActivity).viewModel

        setupRecycleView()

        //set up click on item of recyclerview
        nowPlayingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
                putInt("id", it.id)
            }
            findNavController().navigate(
                R.id.action_movieFragment_to_fragmentmovieDetails,
                bundle
            )

        }

        popularAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
                putInt("id", it.id)
            }
            findNavController().navigate(
                R.id.action_movieFragment_to_fragmentmovieDetails,
                bundle
            )
        }

        topRateAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
                putInt("id", it.id)
            }
            findNavController().navigate(
                R.id.action_movieFragment_to_fragmentmovieDetails,
                bundle
            )
        }

        upComingAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
                putInt("id", it.id)
            }
            findNavController().navigate(
                R.id.action_movieFragment_to_fragmentmovieDetails,
                bundle
            )
        }

        //put movie on viewmodel  and adapter
        viewModel.nowPlayingMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { movieResponse ->
                        nowPlayingAdapter.differ.submitList(movieResponse?.movieList)
                        val totalPage = movieResponse.totalResults / 2
                        viewModel.nowPlayingPage == totalPage
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
            }
        })

        viewModel.popularMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { movieResponse ->
                        popularAdapter.differ.submitList(movieResponse?.movieList)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
            }
        })

        viewModel.topRateMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { movieResponse ->
                        topRateAdapter.differ.submitList(movieResponse?.movieList)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
            }
        })

        viewModel.upComingMovie.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data?.let { movieResponse ->
                        upComingAdapter.differ.submitList(movieResponse?.movieList)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
            }
        })
    }

    private fun setupRecycleView(){
        nowPlayingAdapter = MoviesAdapter()
//        now_playing_movies.apply {
//            adapter = nowPlayingAdapter
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
//            addOnScrollListener(this@MovieFragment.scrollListenerNowPlaying)
//        }
        binding.nowPlayingMovies.apply {
            adapter = nowPlayingAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(this@MovieFragment.scrollListenerNowPlaying)
        }

        popularAdapter = MoviesAdapter()
        binding.popularMovies.apply {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(this@MovieFragment.scrollListenerPopular)
        }

        topRateAdapter = MoviesAdapter()
        binding.toprateMovies.apply {
            adapter = topRateAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(this@MovieFragment.scrollListenerTopRate)
        }

        upComingAdapter = MoviesAdapter()
        binding.upcomingMovies.apply {
            adapter = upComingAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(this@MovieFragment.scrollListenerUpComing)
        }

    }

    val scrollListenerNowPlaying = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val fristVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            if (fristVisibleItemPosition + visibleItemCount >= totalItemCount /2 ) {
                viewModel.nowPlayingPage++
                viewModel.getNowPlaying()
            }
        }
    }
    val scrollListenerPopular = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val fristVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            if (fristVisibleItemPosition + visibleItemCount >= totalItemCount /2 ) {
                viewModel.popularPage++
                viewModel.getPopular()
            }
        }
    }
    val scrollListenerTopRate = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val fristVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            if (fristVisibleItemPosition + visibleItemCount >= totalItemCount /2 ) {
                viewModel.topRatePage++
                viewModel.getTopRate()
            }
        }
    }
    val scrollListenerUpComing = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val fristVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            if (fristVisibleItemPosition + visibleItemCount >= totalItemCount /2 ) {
                viewModel.upComingPage++
                viewModel.getUpComing()
            }
        }
    }



}