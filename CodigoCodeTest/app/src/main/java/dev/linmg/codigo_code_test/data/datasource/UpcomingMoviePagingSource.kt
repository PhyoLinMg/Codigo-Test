package dev.linmg.codigo_code_test.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntity
import dev.linmg.codigo_code_test.data.entity.UpcomingMovieEntity
import dev.linmg.codigo_code_test.data_resources.network.ApiService
import dev.linmg.codigo_code_test.utils.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpcomingMoviePagingSource @Inject constructor(private val apiService: ApiService):PagingSource<Int,UpcomingMovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, UpcomingMovieEntity>): Int=1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingMovieEntity> {
        val page=params.key?: DEFAULT_PAGE_INDEX

        return try{
            val response=apiService.getUpcomingMovies(page = page)
            val previousKey=if(page== DEFAULT_PAGE_INDEX) null else page-1
            val nextKey=if (response.body()?.page==page ) null else page + 1
            val upcomingList=response.body()?.results?.map {
                UpcomingMovieEntity(movieId = it.id)
            }
            LoadResult.Page(
                upcomingList!!, previousKey,nextKey
            )
        }catch (exception: IOException){
            return LoadResult.Error(exception)
        }catch(exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}