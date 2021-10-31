package dev.linmg.codigo_code_test.ui.home


import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.metinozcura.rickandmorty.util.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import dev.linmg.codigo_code_test.R
import dev.linmg.codigo_code_test.data.entity.Movie
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.databinding.FragmentHomeBinding
import dev.linmg.codigo_code_test.databinding.ItemMovieBinding
import dev.linmg.codigo_code_test.databinding.ItemUpcomingMoviesBinding
import dev.linmg.codigo_code_test.ui.adapters.MovieListAdapter
import dev.linmg.codigo_code_test.ui.adapters.UpcomingListAdapter
import dev.linmg.codigo_code_test.utils.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalPagingApi
class HomeFragment:BaseFragment<FragmentHomeBinding,HomeViewModel>(),MovieListAdapter.MovieClickListener,UpcomingListAdapter.MovieClickListener{
    private val homeViewModel:HomeViewModel by viewModels()

    @Inject
    lateinit var popularMovieAdapter:MovieListAdapter

    @Inject
    lateinit var upcomingMovieAdapter: UpcomingListAdapter


    override fun onMovieClicked(binding: ItemMovieBinding, movie: PopularMovieEntityWithMovieItem) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId = movie.movie!!.id))
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun getVM(): HomeViewModel = homeViewModel

    override fun bindVM(binding: FragmentHomeBinding, vm: HomeViewModel) =with(binding){
        with(popularMovieAdapter){
            rvPopularMovies.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }
            rvPopularMovies.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            movieClickListener = this@HomeFragment

            with(vm) {
                launchOnLifecycleScope {
                    popularMoviesFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest { loadStates->
                        if(loadStates.refresh is  LoadState.Error){
                            errorView.visibility= View.VISIBLE
                            layout.visibility=View.GONE
                        }
                    }
                }

            }

        }
        with(upcomingMovieAdapter){
            rvUpcomingMovies.apply {
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
            }

            rvUpcomingMovies.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            movieClickListener = this@HomeFragment


            with(vm) {
                launchOnLifecycleScope {
                    upcomingMoviesFlow.collectLatest { submitData(it) }
                }

            }
        }

    }

    override fun onMovieClicked(
        binding: ItemUpcomingMoviesBinding,
        movie: UpcomingMovieEntityWithMovieItem
    ) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movieId = movie.entity.movieId))
    }

}