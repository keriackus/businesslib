package makeable.intempus.domain.usecases

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.data.http.HttpEndpoint
import makeable.intempus.domain.businesslib.data.http.HttpMethod
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameter
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameterType
import java.util.*
import kotlin.collections.ArrayList

class ApiKeyAuthUsecase(
    actionOrder: Int,
    parentFeature: Feature, private val username: String, private val password: String
) : HttpConnectionUsecase(actionOrder, parentFeature) {

    override fun getEndpoint(): HttpEndpoint {
        return HttpEndpoint(HttpMethod.GET, "web/v1/api_key")
    }
    override fun getServiceParams(): ArrayList<HttpServiceParameter> {
        var params = super.getServiceParams()
        params.add(HttpServiceParameter(HttpServiceParameterType.HEADER, AUTHORIZATION_HEADER_KEY, getBase64encodedHeaderFor(username, password)))
        return params
    }
}