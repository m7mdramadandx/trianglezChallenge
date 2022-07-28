package com.ramadan.netwrok

import android.os.AsyncTask
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


class RequestTask : AsyncTask<String?, Int, List<String>>() {

    override fun doInBackground(vararg uri: String?): List<String> {
        val httpclient: HttpClient = DefaultHttpClient()
        val response: HttpResponse
        var responseString: String? = null

        try {
            response = httpclient.execute(HttpGet(uri.getOrNull(0)))
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
            Log.e("TAGTAG ", e.toString())
        } catch (e: IOException) {
            Log.e("TAGTAG ", e.toString())
        }
        val doc: Document = Jsoup.parse(responseString)
        val elements: Elements = doc.select("p")
        return elements.map { it.text() }
    }

}