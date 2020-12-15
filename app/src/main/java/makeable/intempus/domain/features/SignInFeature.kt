package makeable.intempus.domain.features

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.usecases.ApiKeyAuthUsecase
import makeable.intempus.domain.usecases.BaseAuthUsecase

class SignInFeature(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void, var username: String, var password: String) : Feature(completionBlock) {
    override fun prepareInitialBusinessActions() {
        addBusinessAction(BaseAuthUsecase(businessActions.size,this, username, password))
        addBusinessAction(ApiKeyAuthUsecase(businessActions.size,this, username, password))
    }
}