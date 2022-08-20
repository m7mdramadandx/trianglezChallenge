package com.ramadan.cache.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey


@Keep
@Entity(tableName = "movies_table")
data class MoviesEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val icon: String,
    val color: String,
)