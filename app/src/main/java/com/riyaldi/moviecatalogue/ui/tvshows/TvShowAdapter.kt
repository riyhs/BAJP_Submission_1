package com.riyaldi.moviecatalogue.ui.tvshows

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.riyaldi.moviecatalogue.data.MovieEntity
import com.riyaldi.moviecatalogue.databinding.ItemMovieBinding

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var tvShows = ArrayList<MovieEntity>()

    fun setTvShows(tvShows: ArrayList<MovieEntity>?) {
        if (tvShows == null) return
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    class TvShowViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieEntity) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvGenre.text = tvShow.genre

                val bitmap = BitmapFactory.decodeResource(itemView.context.resources, tvShow.poster)

                Palette.from(bitmap).generate { palette ->
                    val defValue = 0x000000
                    cardItem.setCardBackgroundColor(palette?.getDarkMutedColor(defValue) ?: defValue)

                    Log.d("PALET", "${tvShow.title} == ${palette?.getDarkVibrantColor(defValue)}")
                }

                Glide.with(binding.root.context)
                        .load(tvShow.poster)
                        .transform(RoundedCorners(28))
                        .into(ivPoster)
            }
        }
    }
}