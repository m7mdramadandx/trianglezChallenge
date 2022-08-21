package com.ramadan.cache.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ramadan.cache.entity.MoviesEntity
import java.lang.reflect.Type
import java.util.*

object Converters {

    @TypeConverter
    fun stringToListAreas(data: String?): List<MoviesEntity?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val gson = Gson()
        val listType: Type = object : TypeToken<List<MoviesEntity?>?>() {}.type
        return gson.fromJson<List<MoviesEntity?>>(data, listType)
    }

    @TypeConverter
    fun listAreasToString(someObjects: List<MoviesEntity?>?): String? {
        val gson = Gson()
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}