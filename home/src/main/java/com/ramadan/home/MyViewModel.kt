package com.ramadan.home

import androidx.lifecycle.ViewModel
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MyViewModel : ViewModel() {

    fun fetchData(): InputStream {

        val url = URL("http://www.android.com/")
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        try {
            val inputStream: InputStream = BufferedInputStream(urlConnection.inputStream)
            return inputStream
        } finally {
            urlConnection.disconnect()
        }

//
//        val req: HttpRequest = java.net.http.HttpRequest("http://host:port/path")
//
//        val httpClient = DefaultHttpClient()
//        val httpGet = HttpGet("http://www.someplace.com")
//        val resHandler: ResponseHandler<String> = BasicResponseHandler()
//        val page: String = httpClient.execute(httpGet, resHandler)
    }
}