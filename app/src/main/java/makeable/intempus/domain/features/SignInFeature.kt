package makeable.intempus.domain.features

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.usecases.ApiKeyAuthUsecase
import makeable.intempus.domain.usecases.BaseAuthUsecase

class SignInFeature(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void, var username: String, var password: String) : Feature(completionBlock) {
    override fun prepareInitialBusinessActions() {
        businessActions.add(BaseAuthUsecase(businessActions.size,this, username, password))
        businessActions.add(ApiKeyAuthUsecase(businessActions.size,this, username, password))
    }
}