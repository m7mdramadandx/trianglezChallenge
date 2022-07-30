package com.ramadan.netwrok

import android.os.Handler
import android.os.Looper
import android.util.Log
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.StatusLine
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


abstract class AsyncTasks {

    private val executors: ExecutorService = Executors.newSingleThreadExecutor()

    private fun startBackground(url: String) {
        executors.execute {
            val response = doInBackground(url)
            Handler(Looper.getMainLooper())
                .post {
                    onPostExecute(response)
                }
        }
    }

    fun execute(url: String) {
        startBackground(url)
    }

    abstract fun onPostExecute(list: List<String>)


    private fun doInBackground(url: String): List<String> {
        val httpclient: HttpClient = DefaultHttpClient()
        val response: HttpResponse
        var responseString: String? = null

        try {
            response = httpclient.execute(HttpGet(url))
            val statusLine: StatusLine = response.statusLine
            if (statusLine.statusCode == HttpStatus.SC_OK) {
                val out = ByteArrayOutputStream()
                response.entity.writeTo(out)
                responseString = out.toString()

                out.close()

            } else {
                //Closes the connection.
                response.entity.content.close()
                throw IOException(statusLine.reasonPhrase)
            }
        } catch (e: ClientProtocolException) {
            Log.e(this.javaClass.name, e.toString())
        } catch (e: IOException) {
            Log.e(this.javaClass.name, e.toString())
        }

        return if (responseString != null) {
            val doc: Document = Jsoup.parse(responseString)
            val elements: Elements = doc.select("p")
            elements.map { it.text() } as MutableList<String>
        } else {
            emptyList()
        }
    }

}