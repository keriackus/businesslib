package makeable.intempus.domain.businesslib.data.http

import makeable.intempus.domain.businesslib.data.http.HttpMethod

interface HttpEndpoint {
    fun getMethod() : HttpMethod
    fun getURL() : String
}