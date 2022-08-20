package com.ramadan.netwrok

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

//    @Headers(
//        "Accept: application/json",
//        "Content-Type: application/json"
//    )
    @GET("movie/popular?api_key=b85126b11110ad477526e51ce6b361b6")
    suspend fun getPopularMovies(): MoviesResponse

}