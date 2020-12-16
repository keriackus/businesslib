package makeable.intempus.domain

import makeable.intempus.domain.businesslib.http.HttpEndpoint
import makeable.intempus.domain.businesslib.http.HttpMethod

class IntempusHttpEndpoint(method: HttpMethod, url: String) : HttpEndpoint(method, url) {
    override fun getHttpBaseUrl(): String {
        return "/https://intempus.dk/"
    }
}