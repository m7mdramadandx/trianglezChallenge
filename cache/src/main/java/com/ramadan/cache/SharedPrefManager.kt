package com.ramadan.cache

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefManager @Inject constructor(@ApplicationContext var context: Context) {
    private var sharedPreferences: SharedPreferences? = null

    init {
//        sharedPreferences = SharedPreferences()
    }


    fun setString(key: String, value: String) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.putString(key, value)
            editor.apply()
        }

    }


    fun getString(key: String, def: String? = null): String? {
        sharedPreferences?.let { return it.getString(key, def) }
        return null
    }

    fun setInteger(key: String, value: Int) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.putInt(key, value)
            editor.apply()
        }
    }

    fun getInteger(key: String, def: Int = -1): Int {
        sharedPreferences?.let { return it.getInt(key, def) }
        return def
    }

    fun getLong(key: String): Long {
        sharedPreferences?.let { return it.getLong(key, -1) }
        return -1
    }

    fun setLong(key: String, value: Long) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.putLong(key, value)
            editor.apply()
        }
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }
    }

    fun getBoolean(key: String, def: Boolean = false): Boolean {
        sharedPreferences?.let { return it.getBoolean(key, def) }
        return def
    }

    fun getFloat(key: String): Float {
        sharedPreferences?.let { return it.getFloat(key, 0.0f) }
        return 0.0f
    }

    fun setFloat(key: String, value: Float) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.putFloat(key, value)
            editor.apply()
        }
    }

    fun remove(key: String) {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.remove(key)
            editor.apply()
        }
    }

    fun clearAll() {
        sharedPreferences?.let {
            val editor: SharedPreferences.Editor = it.edit()
            editor.clear()
            editor.apply()
        }
    }
}