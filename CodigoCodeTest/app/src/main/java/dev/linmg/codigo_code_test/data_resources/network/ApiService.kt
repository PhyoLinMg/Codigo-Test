package dev.linmg.codigo_code_test.data_resources.network

import dev.linmg.codigo_code_test.data.response.MovieDetailResponse
import dev.linmg.codigo_code_test.data.response.MovieListResponse
import dev.linmg.codigo_code_test.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
    ): Response<MovieListResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
    ): Response<MovieListResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Response<MovieDetailResponse>

}