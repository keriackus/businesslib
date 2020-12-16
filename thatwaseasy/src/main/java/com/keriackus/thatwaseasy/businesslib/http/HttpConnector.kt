package com.keriackus.thatwaseasy.businesslib.http

import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object HttpConnector {

    fun connect(
        endpoint: HttpEndpoint,
        serviceParams: ArrayList<HttpServiceParameter>
    ) {
        var url = URL(endpoint.url)
        var urlConnection: HttpsURLConnection
        var inStream: InputStream

        urlConnection = url.openConnection() as HttpsURLConnection
        urlConnection.requestMethod =  endpoint.method.name
    }
}