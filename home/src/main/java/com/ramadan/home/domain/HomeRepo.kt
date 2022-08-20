package com.ramadan.home.domain

import com.ramadan.netwrok.MoviesResponse

interface HomeRepo {

    suspend fun getRemotePopularMovies(): MoviesResponse

    fun getLocalMovies(): String?
    fun setLocalMovies(version: String)
}