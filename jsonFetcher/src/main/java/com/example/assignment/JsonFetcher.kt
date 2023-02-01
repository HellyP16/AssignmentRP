package com.example.assignment

/**
 * Created by helly.p on 01/02/23.
 */

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.sql.DriverManager

class JsonFetcher : AsyncTask<String, String, String>() {

    fun getStringFromUrl(url: String): String {
        return this.execute(url).get()
    }

    override fun doInBackground(vararg p0: String?): String {
        var jsonString: String
        var urlConnection: HttpURLConnection?
        val url = URL(p0[0])

        urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        urlConnection.readTimeout = 10000
        urlConnection.connectTimeout = 15000
        urlConnection.doOutput = true
        urlConnection.connect()

        val br = BufferedReader(InputStreamReader(url.openStream()))
        val sb = StringBuilder()
        var line: String?
        line = br.readLine()
        DriverManager.println("line = $line")
        while (line != null) {
            sb.append(line + "\n")
            line = br.readLine()
        }
        br.close()
        jsonString = sb.toString()
        urlConnection.disconnect()
        return jsonString
    }
}