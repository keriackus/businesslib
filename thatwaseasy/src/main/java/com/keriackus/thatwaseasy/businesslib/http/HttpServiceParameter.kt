package com.keriackus.thatwaseasy.businesslib.http

const val USERNAME_KEY = "username"
const val PASSWORD_KEY = "password"

class HttpServiceParameter(val type: HttpServiceParameterType, val key: String, val value: String) {
    companion object {
        fun prepareQueryParams(parameters: ArrayList<HttpServiceParameter>): StringBuilder {
            var queryParamsStr = StringBuilder("?")
            for (param in parameters) {
                if (param.type == HttpServiceParameterType.QUERY_PARAM) {
                    queryParamsStr.append(param.key).append("=").append(param.value).append("&")
                }
            }
            queryParamsStr.deleteCharAt(queryParamsStr.length - 1) //to remove last '&' or '?' if no queryparams
            return queryParamsStr
        }
    }

}