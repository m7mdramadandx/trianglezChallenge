package com.ramadan.home.domain

import com.ramadan.netwrok.data.model.MovieApiModel
import com.ramadan.netwrok.data.response.MoviesResponse
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val homeRepo: HomeRepo,
) {

    suspend fun getPopularMovies(): MoviesResponse {
        val response = homeRepo.getRemotePopularMovies()

        if (response.page == 1) {
            response.movieApiModels?.let {
                homeRepo.setLocalMovies(it)
            }
        }

        return response
    }

    suspend fun getLocalPopularMovies(): List<MovieApiModel> =
        homeRepo.getLocalMovies()

}