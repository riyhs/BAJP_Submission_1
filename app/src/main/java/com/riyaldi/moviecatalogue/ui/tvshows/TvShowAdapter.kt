package com.riyaldi.moviecatalogue.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
                Glide.with(binding.root.context)
                        .load(tvShow.poster)
                        .into(ivPoster)
            }
        }
    }
}