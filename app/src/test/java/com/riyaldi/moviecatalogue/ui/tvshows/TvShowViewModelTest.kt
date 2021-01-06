package com.riyaldi.moviecatalogue.ui.tvshows

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShow = viewModel.getTvShows()
        assertNotNull(tvShow)
        assertEquals(10, tvShow.size)
    }
}