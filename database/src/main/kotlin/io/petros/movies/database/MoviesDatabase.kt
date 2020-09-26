package io.petros.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.petros.movies.database.dao.MoviesDao
import io.petros.movies.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        private const val MOVIES_DATABASE_NAME = "Movies.db"

        @Volatile private var INSTANCE: MoviesDatabase? = null

        @Suppress("SyntheticAccessor")
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            MOVIES_DATABASE_NAME
        ).build()

    }

}
