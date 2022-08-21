package com.ramadan.home.data

import com.ramadan.home.data.source.local.LocalDataSource
import com.ramadan.home.data.source.remote.RemoteDataSource
import com.ramadan.home.domain.HomeRepo
import com.ramadan.netwrok.data.model.MovieApiModel
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : HomeRepo {

    override suspend fun getRemotePopularMovies() =
        remoteDataSource.getRemotePopularMovies()

    override suspend fun getLocalMovies() =
        localDataSource.getLocalMovies()

    override suspend fun setLocalMovies(list: List<MovieApiModel>) =
        localDataSource.setLocalMovies(list)

}

