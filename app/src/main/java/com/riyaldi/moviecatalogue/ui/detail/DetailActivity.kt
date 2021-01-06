package com.riyaldi.moviecatalogue.ui.detail

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.riyaldi.moviecatalogue.data.MovieEntity
import com.riyaldi.moviecatalogue.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var detailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        supportActionBar?.hide()

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val dataId = extras.getString(EXTRA_FILM)
            val dataCategory = extras.getString(EXTRA_CATEGORY)

            if (dataId != null && dataCategory != null) {
                viewModel.setFilm(dataId, dataCategory)
                val film = viewModel.getFilmDetail()
                populateDataDetail(film)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun populateDataDetail(data: MovieEntity) {
        detailBinding.tvDetailGenreDuration.text = "${data.genre} | ${data.duration}"
        detailBinding.collapsing.title = data.title
        detailBinding.tvDetailOverview.text = data.overview

        Glide.with(this)
            .load(data.poster)
            .into(detailBinding.ivDetail)

        val bitmap = BitmapFactory.decodeResource(resources, data.poster)

        Palette.from(bitmap).generate { palette ->
            val defValue = 0xffffff
            detailBinding.cardDetail.setCardBackgroundColor(palette?.getDarkMutedColor(defValue) ?: defValue)
            detailBinding.collapsing.setContentScrimColor(palette?.getDarkMutedColor(defValue) ?: defValue)
            window.statusBarColor = palette?.getDarkMutedColor(defValue) ?: defValue
        }
    }
}