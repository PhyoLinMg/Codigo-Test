package dev.linmg.codigo_code_test.repository

import androidx.paging.*
import dev.linmg.codigo_code_test.data.entity.Movie
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data_resources.db.AppDB
import dev.linmg.codigo_code_test.data_resources.network.ApiService
import dev.linmg.codigo_code_test.utils.Ext
import dev.linmg.codigo_code_test.utils.StatefulData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class MovieRepository @Inject constructor(private val service: ApiService, private val db: AppDB) {

    @ExperimentalPagingApi
    fun getPopularMovies(): Flow<PagingData<PopularMovieEntityWithMovieItem>> {
        if (db == null) throw IllegalStateException("Database is not initialized")

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = Int.MAX_VALUE
            ),
            remoteMediator = PopularMovieMediator(service, db),
            pagingSourceFactory = { db.movieDao().getPopularMovies() }
        ).flow

    }

    @ExperimentalPagingApi
    fun getUpcomingMovies(): Flow<PagingData<UpcomingMovieEntityWithMovieItem>> {
        if (db == null) throw IllegalStateException("Database is not initialized")


        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = Int.MAX_VALUE
            ),
            remoteMediator = UpcomingMovieMediator(service, db)
        ) {
            db.movieDao().getUpcomingMovies()
        }.flow
    }

    fun getMovieDetails(movieId: Int) : Flow<StatefulData<Movie>> = db.movieDao().getMovieDetails(movieId)
        .mapNotNull {  StatefulData.success(it) }
        .onStart {
            val remoteData = Ext.getResponse("Unable to get the movie detail"){
                service.getMovie(movieId)
            }
            when(remoteData.state) {
                StatefulData.DataState.SUCCESS -> db.movieDao().insertMovieDetail(remoteData.data!!.toMovieInfoEntity()) // Data
                else -> emit(remoteData as StatefulData<Movie>)
            }
        }
        .flowOn(Dispatchers.IO)

    fun setFavorite(movieId: Int, isFavorite: Boolean) {
        db.movieDao().setFavouriteMovie(movieId, isFavorite)
    }


}