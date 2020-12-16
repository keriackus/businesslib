package makeable.intempus.domain.SignIn

import makeable.intempus.domain.IntempusHttpConnectionUsecase
import makeable.intempus.domain.IntempusHttpEndpoint
import com.keriackus.thatwaseasy.businesslib.core.Feature
import com.keriackus.thatwaseasy.businesslib.http.HttpEndpoint
import com.keriackus.thatwaseasy.businesslib.http.HttpMethod
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameter
import com.keriackus.thatwaseasy.businesslib.http.HttpServiceParameterType

class BaseAuthUsecase(parentFeature: Feature, private val username: String, private val password:String) : IntempusHttpConnectionUsecase(
    parentFeature) {

    override fun endpoint(): HttpEndpoint {
        return IntempusHttpEndpoint(
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