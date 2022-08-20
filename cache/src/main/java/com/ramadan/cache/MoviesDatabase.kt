package com.ramadan.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ramadan.cache.converter.Converters
import com.ramadan.cache.db.MoviesDao
import com.ramadan.cache.entity.MoviesEntity

@Database(
    version = 1,
    entities = [MoviesEntity::class],
    exportSchema = false,
)
@TypeConverters(Converters::class)

abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMoviesDao(): MoviesDao
}