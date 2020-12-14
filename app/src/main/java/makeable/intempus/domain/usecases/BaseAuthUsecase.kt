package makeable.intempus.domain.usecases

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.data.http.HttpEndpoint
import makeable.intempus.domain.businesslib.data.http.HttpMethod
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameter
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameterType

class BaseAuthUsecase(actionOrder: Int, parentFeature: Feature, private val username: String, private val password:String) : HttpConnectionUsecase(actionOrder,
    parentFeature) {

    override fun getEndpoint(): HttpEndpoint {
        return HttpEndpoint(HttpMethod.POST, "api/init")
    }
    override fun getServiceParams(): ArrayList<HttpServiceParameter> {
        var params = super.getServiceParams()
        params.add(HttpServiceParameter(HttpServiceParameterType.BODY, "username", username))
        params.add(HttpServiceParameter(HttpServiceParameterType.BODY, "password", password))
        return params
    }
}