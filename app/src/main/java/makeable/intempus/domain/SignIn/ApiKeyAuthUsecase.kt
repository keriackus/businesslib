package makeable.intempus.domain.SignIn

import makeable.intempus.domain.IntempusHttpConnectionUsecase
import makeable.intempus.domain.businesslib.core.AUTHORIZATION_HEADER_KEY
import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.http.HttpEndpoint
import makeable.intempus.domain.businesslib.http.HttpMethod
import makeable.intempus.domain.businesslib.http.HttpServiceParameter
import makeable.intempus.domain.businesslib.http.HttpServiceParameterType
import kotlin.collections.ArrayList

class ApiKeyAuthUsecase(
    parentFeature: Feature, private val username: String, private val password: String
) : IntempusHttpConnectionUsecase(parentFeature) {

    override fun endpoint(): HttpEndpoint {

        return HttpEndpoint(
            HttpMethod.GET,
            "web/v1/api_key"
        )
    }

    override fun serviceParams(): ArrayList<HttpServiceParameter> {
        var params = super.serviceParams()
        params.add(
            HttpServiceParameter(
                HttpServiceParameterType.HEADER,
                AUTHORIZATION_HEADER_KEY,
                getBase64encodedHeaderFor(username, password)
            )
        )
        return params
    }
}