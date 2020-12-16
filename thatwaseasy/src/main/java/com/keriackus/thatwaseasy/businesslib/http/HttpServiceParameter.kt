package com.keriackus.thatwaseasy.businesslib.http

const val USERNAME_KEY = "username"
const val PASSWORD_KEY = "password"
class HttpServiceParameter(val type : HttpServiceParameterType, val key : String, val value: String) {
}