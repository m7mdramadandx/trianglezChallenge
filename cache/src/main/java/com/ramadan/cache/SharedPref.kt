package com.ramadan.cache

import com.google.gson.Gson
import com.ramadan.cache.extension_function.GsonExtension.fromJsonGeneric
import com.ramadan.cache.extension_function.GsonExtension.toJsonGeneric
import javax.inject.Inject

class SharedPref @Inject constructor(val sharedPrefManager: SharedPrefManager) {

    inline fun <reified T> getLocalObject(key: String): T {
        val jsonString = sharedPrefManager.getString(key, "") ?: ""
        val json = Gson()
        return json.fromJsonGeneric(jsonString)
    }

    inline fun <reified T> setLocalObject(key: String, genericObject: T) {
        val json = Gson()
        val jsonString = json.toJsonGeneric(genericObject)
        sharedPrefManager.setString(key, jsonString)
    }

    fun removeObject(key: String) {
        sharedPrefManager.remove(key)
    }

}