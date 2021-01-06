package com.riyaldi.moviecatalogue.ui.movies

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.riyaldi.moviecatalogue.data.MovieEntity
import com.riyaldi.moviecatalogue.databinding.ItemMovieBinding


class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = ArrayList<MovieEntity>()

    fun setMovies(movies: ArrayList<MovieEntity>?){
        if (movies == null) return
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvGenre.text = movie.genre

                val bitmap = BitmapFactory.decodeResource(itemView.context.resources, movie.poster)

                Palette.from(bitmap).generate { palette ->
                    val defValue = 0xf8f8f8
                    cardItem.setCardBackgroundColor(palette?.getDarkMutedColor(defValue) ?: defValue)
                }

                Glide.with(itemView.context)
                        .load(movie.poster)
                        .transform(RoundedCorners(28))
                        .into(ivPoster)
            }
        }
    }
}