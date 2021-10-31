package dev.linmg.codigo_code_test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmg.codigo_code_test.data.entity.Movie
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.repository.MovieRepository
import dev.linmg.codigo_code_test.utils.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {
    private lateinit var _popularMoviesFlow:Flow<PagingData<PopularMovieEntityWithMovieItem>>
    val popularMoviesFlow:Flow<PagingData<PopularMovieEntityWithMovieItem>> get() =  _popularMoviesFlow

    private lateinit var _upcomingMoviesFlow:Flow<PagingData<UpcomingMovieEntityWithMovieItem>>
    val upcomingMoviesFlow:Flow<PagingData<UpcomingMovieEntityWithMovieItem>> get() = _upcomingMoviesFlow


    init {
        getPopularMovies()
        getUpcomingMovies()
    }
    private fun getPopularMovies() =launchPagingAsync({
        movieRepository.getPopularMovies().cachedIn(viewModelScope)
    },{
        _popularMoviesFlow=it
    })

    private fun getUpcomingMovies() = launchPagingAsync({
        movieRepository.getUpcomingMovies()
    },{
        _upcomingMoviesFlow=it
    })



}