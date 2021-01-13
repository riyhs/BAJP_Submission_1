package com.riyaldi.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.riyaldi.moviecatalogue.data.MovieEntity
import com.riyaldi.moviecatalogue.utils.DataDummy

class DetailViewModel: ViewModel() {
    companion object {
        const val TV_SHOW = "tvShow"
        const val MOVIE = "movie"
    }

    private lateinit var film: MovieEntity

    fun setFilm(id: String, category: String) {
        when (category) {
            TV_SHOW -> {
                for (tvShow in DataDummy.getTvShows()) {
                    if (tvShow.id == id) film = tvShow
                }
            }

            MOVIE -> {
                for (movie in DataDummy.getMovies()) {
                    if (movie.id == id) film = movie
                }
            }
        }
    }

    fun getFilmDetail() = film

}