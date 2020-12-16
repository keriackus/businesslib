package com.keriackus.thatwaseasy.businesslib.core

import com.keriackus.thatwaseasy.businesslib.http.HttpConnector
import com.keriackus.thatwaseasy.businesslib.http.HttpEndpoint
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameter

const val AUTHORIZATION_HEADER_KEY = "Authorization"

abstract class HttpConnectionUsecase
    : Usecase {
    override suspend fun doTheJob() {
        super.doTheJob()
        doConnection()
    }

    private fun doConnection() {
        HttpConnector.connect(endpoint(), serviceParams())
    }

    protected abstract fun endpoint(): HttpEndpoint
    open fun serviceParams(): ArrayList<HttpServiceParameter> {
        var params = ArrayList<HttpServiceParameter>()
        return params
    }

    protected abstract fun getHttpBaseUrl(): String

    protected fun getBase64encodedHeaderFor(username: String, password: String): String {
        var usernameColonPassword = username + ":" + password
        return "Basic " + usernameColonPassword.toByteArray()
    }

    fun getHttpUrl(): String {
        return getHttpBaseUrl() + endpoint().url
    }
    fun getMethod(): String {
       return  endpoint().method.name
    }
    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Void) : super(completionBlock)

}