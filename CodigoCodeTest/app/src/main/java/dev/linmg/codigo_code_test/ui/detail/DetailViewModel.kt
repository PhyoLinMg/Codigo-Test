package dev.linmg.codigo_code_test.ui.detail

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.linmg.codigo_code_test.data.entity.Movie
import dev.linmg.codigo_code_test.data_resources.db.AppDB
import dev.linmg.codigo_code_test.repository.MovieRepository
import dev.linmg.codigo_code_test.utils.StatefulData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel
@Inject constructor(state:SavedStateHandle,private val movieRepository: MovieRepository) : ViewModel() {

    private var movieId: Int= -1


    private val _movieDetails : MutableLiveData<StatefulData<Movie>> = MutableLiveData(StatefulData.loading())
    val movieDetails : LiveData<StatefulData<Movie>> = _movieDetails



    init {
        viewModelScope.launch {
            movieId = state.get<Int>("movieId")!!.toInt()
            fetchMovieDetails()
        }
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId).collect{
                _movieDetails.postValue(it)
            }
        }
    }

    fun onTapFavourite() {
        val isFavourite = _movieDetails.value?.data?.isFavourite ?: false
        movieRepository.setFavorite(movieId , !isFavourite)
    }


}