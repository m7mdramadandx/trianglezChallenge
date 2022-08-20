package com.ramadan.home.data.source.remote

import com.ramadan.netwrok.MoviesResponse

interface RemoteDataSource {

    suspend fun getRemotePopularMovies(): MoviesResponse
}