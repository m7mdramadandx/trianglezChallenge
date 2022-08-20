package com.ramadan.home.data.source.remote

import com.ramadan.netwrok.ApiService
import com.ramadan.netwrok.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {


    override suspend fun getRemotePopularMovies(): MoviesResponse =
        withContext(Dispatchers.IO) {
            apiService.getPopularMovies()
        }

}