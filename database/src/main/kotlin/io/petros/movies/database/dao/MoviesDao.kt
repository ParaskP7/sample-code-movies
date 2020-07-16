package io.petros.movies.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.petros.movies.database.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("DELETE FROM movies")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun movies(): PagingSource<Int, MovieEntity>

}
