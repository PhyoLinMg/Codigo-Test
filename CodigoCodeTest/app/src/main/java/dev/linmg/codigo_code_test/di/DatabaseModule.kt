package dev.linmg.codigo_code_test.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.linmg.codigo_code_test.data_resources.db.AppDB
import dev.linmg.codigo_code_test.data_resources.db.daos.MovieDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context):AppDB{
        return AppDB.getDatabase(appContext)
    }

    @Provides
    fun provideMovieDao(appDB: AppDB) : MovieDao{
        return appDB.movieDao()
    }



}