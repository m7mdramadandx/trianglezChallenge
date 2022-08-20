package com.ramadan.home.domain

import com.ramadan.netwrok.MoviesResponse
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepo: HomeRepo,
) {

    suspend fun getPopularMovies(): MoviesResponse {
        val response = homeRepo.getRemotePopularMovies()

//        response.configuration?.let {
//            val currentVersion = homeRepo.getConfigurationVersion()
//            // if user has no config version
//            if (currentVersion.isNullOrEmpty()) {
//                getConfiguration()
//                homeRepo.setConfigurationVersion(it.currentVersion)
//
//            } else {
//                // check if user has the latest config version
//                val _currentVersion = currentVersion.replace(".", "").toInt()
//                val apiVersion = it.currentVersion.replace(".", "").toInt()
//
//                // update config if user has old config version
//                if (apiVersion > _currentVersion) {
//                    getConfiguration()
//                    homeRepo.setConfigurationVersion(it.currentVersion)
//                }
//            }
//        }

        return response
    }

}