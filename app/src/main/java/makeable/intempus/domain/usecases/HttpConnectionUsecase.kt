package makeable.intempus.domain.usecases

import makeable.intempus.domain.businesslib.core.ConnectionUsecase
import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameter
const val AUTHORIZATION_HEADER_KEY = "Authorization"
abstract class HttpConnectionUsecase(parentFeature: Feature
) : ConnectionUsecase(parentFeature) {
    override fun doConnection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServiceParams(): ArrayList<HttpServiceParameter> {
        var params = ArrayList<HttpServiceParameter>()
        return params
    }
    fun getBase64encodedHeaderFor(username: String, password: String): String {
        var usernameColonPassword = username + ":" + password
        return "Basic " + usernameColonPassword.toByteArray()
    }
}