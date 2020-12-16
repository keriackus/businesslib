package makeable.intempus.domain.SignIn

import makeable.intempus.domain.IntempusHttpConnectionUsecase
import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.http.HttpEndpoint
import makeable.intempus.domain.businesslib.http.HttpMethod
import makeable.intempus.domain.businesslib.http.HttpServiceParameter
import makeable.intempus.domain.businesslib.http.HttpServiceParameterType

class BaseAuthUsecase(parentFeature: Feature, private val username: String, private val password:String) : IntempusHttpConnectionUsecase(
    parentFeature) {

    override fun endpoint(): HttpEndpoint {
        return HttpEndpoint(
            HttpMethod.POST,
            "api/init"
        )
    }
    override fun serviceParams(): ArrayList<HttpServiceParameter> {
        var params = super.serviceParams()
        params.add(
            HttpServiceParameter(
                HttpServiceParameterType.BODY,
                "username",
                username
            )
        )
        params.add(
            HttpServiceParameter(
                HttpServiceParameterType.BODY,
                "password",
                password
            )
        )
        return params
    }
}