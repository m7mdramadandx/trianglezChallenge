package com.ramadan.home.data.source.local

import com.ramadan.netwrok.data.model.MovieApiModel

interface LocalDataSource {

    suspend fun getLocalMovies(): List<MovieApiModel>

    suspend fun setLocalMovies(list: List<MovieApiModel>)

}