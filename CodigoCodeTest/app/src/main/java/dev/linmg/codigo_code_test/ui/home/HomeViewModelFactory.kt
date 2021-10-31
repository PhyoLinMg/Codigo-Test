package dev.linmg.codigo_code_test.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import dev.linmg.codigo_code_test.repository.MovieRepository
import javax.inject.Inject

@ExperimentalPagingApi
class HomeViewModelFactory @Inject constructor(private val movieRepository: MovieRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(movieRepository) as T
    }
}