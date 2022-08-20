package com.ramadan.home.data.source.local

interface LocalDataSource {

    fun getLocalMovies(): String?

    fun setLocalMovies(version: String)

}