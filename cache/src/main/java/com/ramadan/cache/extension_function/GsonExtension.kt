package com.ramadan.cache.extension_function

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GsonExtension {

    inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type

    inline fun <reified T> Gson.fromJsonGeneric(jsonString: String): T {
        val type = genericType<T>()
        return fromJson(jsonString, type)
    }

    inline fun <reified T> Gson.toJsonGeneric(genericObject: T): String {
        val type = genericType<T>()
        return toJson(genericObject, type)
    }

}