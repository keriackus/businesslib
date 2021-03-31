package com.keriackus.thatwaseasy.businesslib.core

import com.keriackus.thatwaseasy.businesslib.http.HttpConnector
import com.keriackus.thatwaseasy.businesslib.http.HttpEndpoint
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.Set
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

const val AUTHORIZATION_HEADER_KEY = "Authorization"

abstract class HttpConnectionUsecase
    : Usecase {
    protected var isPayloadJson = false
    override suspend fun doTheJob() {
        super.doTheJob()
        val response = HttpConnector.connect(endpoint(), serviceParams())
        modifyDatabase(response)
        moveOn(null)
    }


    protected fun modifyDatabase(response: String) {
        val x = 2
    }

    protected abstract fun endpoint(): HttpEndpoint
    open fun serviceParams(): ArrayList<HttpServiceParameter> {
        var params = ArrayList<HttpServiceParameter>()
        return params
    }

    protected open fun preparePayload(
        payloadMap: MutableMap<String, String>
    ): String {
        val body: StringBuilder
        if (isPayloadJson) {
            body = StringBuilder()
            for (key in payloadMap.keys) {
                body.append("\"").append(key).append("\"").append(":").append("\"")
                    .append(payloadMap[key]).append("\"").append(",")
            }
            if (body.length > 0) {
                body.insert(0, '{')
                body.deleteCharAt(body.lastIndexOf(","))
                body.append('}')
            }
        } else {
            val entries: Set<Map.Entry<String, String>> = payloadMap.entries
            body = StringBuilder("")
            for ((key, value) in entries) {
                body.append(key).append("=").append(value).append("&")
            }
            body.deleteCharAt(body.length - 1) //removes last ampersand
        }
        return body.toString()
    }

    protected abstract fun getHttpBaseUrl(): String

    protected fun getBase64encodedHeaderFor(username: String, password: String): String {
        var usernameColonPassword = username + ":" + password
        return "Basic " + usernameColonPassword.toByteArray()
    }


    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit) : super(
        completionBlock
    )

}