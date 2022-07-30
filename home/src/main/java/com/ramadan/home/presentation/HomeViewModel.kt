package com.ramadan.home.presentation

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadan.cache.DatabaseHelper
import com.ramadan.home.utils.isNetworkConnected
import com.ramadan.netwrok.AsyncTasks
import java.util.*


class HomeViewModel : ViewModel() {

    companion object {
        const val URL = "https://instabug.com"
    }

    fun fetchData(): MutableLiveData<List<Pair<String, Int>>> {
        val mutableLiveData = MutableLiveData<List<Pair<String, Int>>>()

        object : AsyncTasks() {
            override fun onPostExecute(list: List<String>) {
                Handler(Looper.getMainLooper()).postDelayed({

                    /**
                     * convert all chars to lower case
                     * remove special characters
                     * split the full words with a space to catch each word in a list form
                     * grouping words with them selves to catch el repeated ones
                     * put the count of each repeated word
                     * convert the map to list of pairs
                     * remove empty items from list
                     * sort list by descending order
                     */
                    val repeatedWordsList = list.toString()
                        .lowercase(Locale.getDefault())
                        .replace(
                            Regex("[$&+,:;=?@#|/¿§«»ω⊙¤°℃℉€¥£¢¡®©0‘'\\[\\]<>→“\".^*()%!-]"),
                            ""
                        )
                        .split(" ")
                        .groupingBy { it }
                        .eachCount()
                        .toList()
                        .filterNot { it.first == "" }
                        .sortedByDescending { it.second }
                    mutableLiveData.value = repeatedWordsList

                }, 1000L)
            }
        }.execute(URL)

        return mutableLiveData
    }

    fun fetchOfflineData(context: Context): MutableLiveData<List<Pair<String, Int>>> {
        val databaseOpenHelper = DatabaseHelper(context)
        val mutableLiveData = MutableLiveData<List<Pair<String, Int>>>()

        val response = databaseOpenHelper.readCourses()
        mutableLiveData.value = response

        return mutableLiveData
    }

    fun insert(context: Context, list: List<Pair<String, Int>>) {
        val databaseOpenHelper = DatabaseHelper(context)

        list.forEach {
            databaseOpenHelper.addNewWord(it)
        }
    }

    fun checkInternetConnection(context: Context): MutableLiveData<Boolean> {
        val mutableLiveData = MutableLiveData<Boolean>()
        val response = isNetworkConnected(context)
        mutableLiveData.value = response

        return mutableLiveData
    }

}