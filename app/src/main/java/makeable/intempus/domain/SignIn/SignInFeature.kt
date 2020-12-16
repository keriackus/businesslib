package makeable.intempus.domain.SignIn

import com.keriackus.thatwaseasy.businesslib.core.Feature

class SignInFeature(
    completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Void,
    var username: String,
    var password: String
) : Feature(completionBlock) {
    override fun prepareInitialBusinessActions() {
        addBusinessAction(
            BaseAuthUsecase(
                this,
                username,
                password
            )
        )
        addBusinessAction(
            ApiKeyAuthUsecase(
                this,
                username,
                password
            )
        )
    }
}