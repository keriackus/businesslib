package com.keriackus.thatwaseasy.businesslib.http

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.text.Charsets.UTF_8

object HttpConnector {

  suspend fun connect(
        endpoint: HttpEndpoint,
        serviceParams: ArrayList<HttpServiceParameter>
    ) :String {
        var url = URL(endpoint.getURL() + HttpServiceParameter.prepareQueryParams(serviceParams))
        var urlConnection: HttpsURLConnection
        urlConnection = url.openConnection() as HttpsURLConnection
        urlConnection.requestMethod = endpoint.method.name


        for (param in serviceParams) {
            when (param.type) {
                HttpServiceParameterType.BODY -> setBody(param.value, urlConnection)
            }
            addCookies(serviceParams, urlConnection)
            addHeaders(serviceParams, urlConnection)

        }

        var response  = StringBuilder()
        var inStream =
            if (isHttpOk(urlConnection.responseCode)) urlConnection.inputStream else urlConnection.errorStream

        val bufferedReader =  BufferedReader(InputStreamReader(inStream))
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            response.append(line)
        }

        return response.toString()
    }

    private fun setBody(payload: String, urlConnection: HttpURLConnection) {
        urlConnection.doOutput = true;
        urlConnection.setFixedLengthStreamingMode(payload.toByteArray(UTF_8).size)
        var out = PrintWriter(urlConnection.outputStream)
        out.print(payload)
        out.close()
    }

    private fun addCookies(
        parameters: ArrayList<HttpServiceParameter>,
        urlConnection: HttpURLConnection
    ) {
        var cookies = StringBuilder()
        for (param in parameters) {
            if (param.type == HttpServiceParameterType.COOKIE) {
                cookies.append(param.key).append("=").append(param.value).append(";")
            }
            if (cookies.isNotEmpty()) {
                urlConnection.setRequestProperty("Cookie", cookies.substring(0, cookies.length - 1))
            }
        }
    }

    private fun addHeaders(
        parameters: ArrayList<HttpServiceParameter>,
        urlConnection: HttpURLConnection
    ) {
        for (param in parameters) {
            if (param.type == HttpServiceParameterType.HEADER) {
                urlConnection.setRequestProperty(param.key, param.value)
            }
        }
    }

    private fun isHttpOk(statusCode: Int): Boolean {
        return statusCode in 200..299
    }
}