package com.example.moviereview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.moviereview.R
import com.oxcoding.moviemvvm.data.vo.MovieDetails
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<MovieDetails>(){
        override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((MovieDetails) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie =differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(item_movie_poster)
            item_movie_title.text = movie.title
            item_realse_date.text = movie.releaseDate

            setOnClickListener {
                onItemClickListener?.let { it(movie) }
            }
        }
    }

    fun setOnItemClickListener(listener: (MovieDetails) -> Unit) {
        onItemClickListener = listener
    }



}