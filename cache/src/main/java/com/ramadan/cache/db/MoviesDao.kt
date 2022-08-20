package com.ramadan.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramadan.cache.entity.MoviesEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecialties(list: List<MoviesEntity>)

    @Query("SELECT * FROM movies_table")
    suspend fun getSpecialties(): List<MoviesEntity>


    @Query("DELETE FROM movies_table")
    suspend fun clearSpecialties()
}