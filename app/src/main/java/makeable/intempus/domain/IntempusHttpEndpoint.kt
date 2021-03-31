package makeable.intempus.domain

import com.keriackus.thatwaseasy.businesslib.http.HttpEndpoint
import com.keriackus.thatwaseasy.businesslib.http.HttpMethod

class IntempusHttpEndpoint(method: HttpMethod, url: String) : HttpEndpoint(method, url) {
    override fun getHttpBaseUrl(): String {
        return "https://intempus.dk/"
    }
}