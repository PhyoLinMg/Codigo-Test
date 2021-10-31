package dev.linmg.codigo_code_test.data_resources.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metinozcura.rickandmorty.data.db.converter.StringListConverter
import dev.linmg.codigo_code_test.data.entity.*
import dev.linmg.codigo_code_test.data_resources.db.daos.MovieDao
import dev.linmg.codigo_code_test.data_resources.db.daos.RemoteKeyDao

@Database(
    entities = [
        Movie::class,
        PopularMovieEntity::class,
        UpcomingMovieEntity::class,
        MovieDetailEntity::class,
        RemoteKeys::class,
    ], version = 1, exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao


    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, "MovieDatabase.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .enableMultiInstanceInvalidation()
                .build()
    }
}