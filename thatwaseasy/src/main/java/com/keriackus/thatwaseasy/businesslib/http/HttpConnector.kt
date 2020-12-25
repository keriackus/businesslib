package com.keriackus.thatwaseasy.businesslib.http

import com.keriackus.thatwaseasy.businesslib.core.HttpConnectionUsecase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import javax.xml.bind.JAXBElement

object HttpConnector {

    fun connect(usecase: HttpConnectionUsecase) {

        GlobalScope.launch {
            delay(2000)
            usecase.moveOn(null)
        }
/*        var url = URL(endpoint.url)
        var urlConnection: HttpsURLConnection
        var inStream: InputStream
        urlConnection = url.openConnection() as HttpsURLConnection
        urlConnection.requestMethod =  endpoint.method.name*/
    }
}