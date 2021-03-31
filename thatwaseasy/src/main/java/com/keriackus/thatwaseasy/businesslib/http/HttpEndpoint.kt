package com.keriackus.thatwaseasy.businesslib.http

abstract class HttpEndpoint(val method: HttpMethod, private val relativeURL: String) {
    protected abstract fun getHttpBaseUrl(): String
    fun getURL() : String{
        return getHttpBaseUrl() + relativeURL
    }
}