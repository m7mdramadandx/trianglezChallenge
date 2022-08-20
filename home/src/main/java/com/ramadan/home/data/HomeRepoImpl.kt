package com.ramadan.home.data

import com.ramadan.home.data.source.local.LocalDataSource
import com.ramadan.home.data.source.remote.RemoteDataSource
import com.ramadan.home.domain.HomeRepo
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : HomeRepo {

    override suspend fun getRemotePopularMovies() = remoteDataSource.getRemotePopularMovies()

    override fun getLocalMovies() =
        localDataSource.getLocalMovies()

    override fun setLocalMovies(version: String) =
        localDataSource.setLocalMovies(version)

}

