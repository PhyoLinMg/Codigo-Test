package dev.linmg.codigo_code_test.di

import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.linmg.codigo_code_test.data_resources.db.AppDB
import dev.linmg.codigo_code_test.data_resources.network.ApiService
import dev.linmg.codigo_code_test.repository.MovieRepository
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    @ExperimentalPagingApi
    fun provideMovieRepository(service:ApiService,appDB: AppDB) = MovieRepository(service,appDB)
}