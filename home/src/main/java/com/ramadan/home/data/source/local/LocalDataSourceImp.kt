package com.ramadan.home.data.source.local

import com.ramadan.cache.SharedPref
import javax.inject.Inject


class LocalDataSourceImp @Inject constructor(
    private val sharedPref: SharedPref
) : LocalDataSource {

    override fun getLocalMovies(): String? {
        TODO("Not yet implemented")
    }

    override fun setLocalMovies(version: String) {
        TODO("Not yet implemented")
    }

}