package com.ramadan.home.data.source.local

import com.ramadan.cache.db.MoviesDao
import com.ramadan.cache.entity.MoviesEntity
import com.ramadan.netwrok.data.model.MovieApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalDataSourceImp @Inject constructor(
    private val moviesDao: MoviesDao
) : LocalDataSource {

    override suspend fun getLocalMovies(): List<MovieApiModel> =
        withContext(Dispatchers.IO) {
            val response = moviesDao.getMovies()
            if (response.isEmpty()) emptyList()
            else movieListEntityToMovieApiModelList(response)
        }


    override suspend fun setLocalMovies(list: List<MovieApiModel>) {
        withContext(Dispatchers.IO) {
            val movieListEntity = movieApiModelListToMovieListEntity(list)
            moviesDao.insertMovies(movieListEntity)
        }
    }


    private fun movieApiModelListToMovieListEntity(list: List<MovieApiModel>): List<MoviesEntity> {
        return list.map {
            MoviesEntity(
                id = it.id,
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        }

    }

    private fun movieListEntityToMovieApiModelList(list: List<MoviesEntity>): List<MovieApiModel> {
        return list.map {
            MovieApiModel(
                id = it.id,
                adult = it.adult,
                backdrop_path = it.backdrop_path,
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                release_date = it.release_date,
                title = it.title,
                video = it.video,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                genre_ids = emptyList()
            )
        }

    }

}