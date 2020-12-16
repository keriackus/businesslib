package makeable.intempus.domain.businesslib.http

abstract class HttpEndpoint(val method: HttpMethod) {
    var url  : String = ""
        get() {
         return getHttpBaseUrl() + field
        }
        set(value) {}

    protected abstract fun getHttpBaseUrl(): String
    constructor(method: HttpMethod, url: String): this(method){
        this.url = url
    }

}