package com.ramadan.home.domain

import com.ramadan.netwrok.data.model.MovieApiModel
import com.ramadan.netwrok.data.response.MoviesResponse

interface HomeRepo {

    suspend fun getRemotePopularMovies(): MoviesResponse

    suspend fun getLocalMovies(): List<MovieApiModel>
    suspend fun setLocalMovies(list: List<MovieApiModel>)
}