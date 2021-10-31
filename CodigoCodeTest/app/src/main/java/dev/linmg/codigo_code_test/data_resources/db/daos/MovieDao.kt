package dev.linmg.codigo_code_test.data_resources.db.daos

import androidx.paging.PagingSource
import androidx.room.*
import dev.linmg.codigo_code_test.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies() : PagingSource<Int,PopularMovieEntityWithMovieItem>

    @Query("SELECT * FROM upcoming_movies")
    fun getUpcomingMovies() : PagingSource<Int,UpcomingMovieEntityWithMovieItem>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieDetails(movieId : Int) : Flow<Movie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovies:List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(upcomingMovies:List<UpcomingMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(item:List<Movie>)

    @Update(entity=Movie::class)
    fun insertMovieDetail(item:MovieDetailEntity)

    @Query("UPDATE movies SET isFavourite = :isFavourite WHERE id = :movieId")
    fun setFavouriteMovie(movieId : Int , isFavourite : Boolean)

    @Query("DELETE FROM movies")
    fun deleteAll()



}