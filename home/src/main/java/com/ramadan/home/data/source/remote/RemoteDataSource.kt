package com.ramadan.home.data.source.remote

import com.ramadan.netwrok.data.response.MoviesResponse

interface RemoteDataSource {

    suspend fun getRemotePopularMovies(): MoviesResponse
}