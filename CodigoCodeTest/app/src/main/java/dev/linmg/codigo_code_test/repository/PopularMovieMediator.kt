package dev.linmg.codigo_code_test.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntity
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntityWithMovieItem
import dev.linmg.codigo_code_test.data.entity.RemoteKeys
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntity
import dev.linmg.codigo_code_test.data_resources.db.AppDB
import dev.linmg.codigo_code_test.data_resources.network.ApiService
import dev.linmg.codigo_code_test.utils.DEFAULT_PAGE_INDEX
import dev.linmg.codigo_code_test.utils.UPCOMING_MOVIE_TYPE
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

@ExperimentalPagingApi
class PopularMovieMediator @Inject constructor(
    private val apiService: ApiService,
    private val appDB: AppDB
) : RemoteMediator<Int, PopularMovieEntityWithMovieItem>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieEntityWithMovieItem>
    ): MediatorResult {
        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }
        Log.d("page",page.toString())

        try {
            val response = apiService.getPopularMovies(page = page)

            val isEndOfList = page == response.body()?.totalPages

            Log.d("isEndOfList",isEndOfList.toString())
            appDB.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDB.remoteKeyDao().clearRemoteKeys()
                    appDB.movieDao().deleteAll()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                Log.d("keys:","prevKey $prevKey and nextKey $nextKey")
                val keys = response.body()?.results?.map {
                    RemoteKeys(
                        movieId = it.id.toString(),
                        type = UPCOMING_MOVIE_TYPE,
                        prevKey = prevKey,
                        nextKey = nextKey,
                    )
                }
                appDB.remoteKeyDao().insertAll(keys!!)
                appDB.movieDao().insertMovies(response.body()?.results!!.map { it.toMovie() })
                appDB.movieDao()
                    .insertPopularMovies(response.body()?.results!!.map { it.toMovie() }
                        .map { PopularMovieEntity(movieId = it.id) })
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieEntityWithMovieItem>
    ): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                Log.d("nextKey in APPEND",nextKey.toString())
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
                prevKey
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, PopularMovieEntityWithMovieItem>): RemoteKeys? {
        val rr= state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie ->
               Log.d("movieId","the movie Id ${movie.entity.movieId}")
                appDB.remoteKeyDao().remoteKeyMovieId(
                    movie.entity.movieId.toString(),
                )
            }
        Log.d("rr",rr.toString())
        return rr
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, PopularMovieEntityWithMovieItem>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie ->
                Log.d("movieId","the movie Id ${movie.entity.movieId}")
                appDB.remoteKeyDao().remoteKeyMovieId(
                    movie.entity.movieId.toString()

                )
            }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, PopularMovieEntityWithMovieItem>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.entity?.movieId?.let { repoId ->
                appDB.remoteKeyDao().remoteKeyMovieId(repoId.toString())
            }
        }
    }
}