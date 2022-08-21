package com.ramadan.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramadan.cache.entity.MoviesEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(list: List<MoviesEntity>)

    @Query("SELECT * FROM movies_table")
    suspend fun getMovies(): List<MoviesEntity>


    @Query("DELETE FROM movies_table")
    suspend fun clearSpecialties()
}