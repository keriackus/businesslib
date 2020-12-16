package makeable.intempus.domain.SignIn

import makeable.intempus.domain.IntempusHttpConnectionUsecase
import makeable.intempus.domain.IntempusHttpEndpoint
import com.keriackus.thatwaseasy.businesslib.core.AUTHORIZATION_HEADER_KEY
import com.keriackus.thatwaseasy.businesslib.core.Feature
import com.keriackus.thatwaseasy.businesslib.http.HttpEndpoint
import com.keriackus.thatwaseasy.businesslib.http.HttpMethod
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameter
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameterType
import kotlin.collections.ArrayList

class ApiKeyAuthUsecase(
    parentFeature: Feature, private val username: String, private val password: String
) : IntempusHttpConnectionUsecase(parentFeature) {

    override fun endpoint(): HttpEndpoint {

        return IntempusHttpEndpoint(
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