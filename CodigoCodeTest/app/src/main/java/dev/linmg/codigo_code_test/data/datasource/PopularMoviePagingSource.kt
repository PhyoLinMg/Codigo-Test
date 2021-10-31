package dev.linmg.codigo_code_test.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.linmg.codigo_code_test.data.entity.PopularMovieEntity
import dev.linmg.codigo_code_test.data_resources.network.ApiService
import dev.linmg.codigo_code_test.utils.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PopularMoviePagingSource @Inject constructor(private val apiService: ApiService):
    PagingSource<Int, PopularMovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PopularMovieEntity>): Int=1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieEntity> {
        val page=params.key?: DEFAULT_PAGE_INDEX

        return try{
            val response=apiService.getPopularMovies(page = page)
            val previousKey=if(page== DEFAULT_PAGE_INDEX) null else page-1
            val nextKey=if (page==response.body()?.totalPages) null else page + 1
            val popularList=response.body()?.results?.map {
                PopularMovieEntity(movieId = it.id)
            }
            LoadResult.Page(
                popularList!!, previousKey,nextKey
            )
        }catch (exception:IOException){
            return LoadResult.Error(exception)
        }catch(exception:HttpException){
            return LoadResult.Error(exception)
        }


    }
}