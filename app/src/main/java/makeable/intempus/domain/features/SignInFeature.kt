package makeable.intempus.domain.features

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.usecases.ApiKeyAuthUsecase
import makeable.intempus.domain.usecases.BaseAuthUsecase

class SignInFeature(actionOrder: Int, parentFeature: Feature) : Feature(actionOrder, parentFeature) {

    override fun execute() {
        super.execute()
        businessActions.add(BaseAuthUsecase(businessActions.size,this))
        businessActions.add(ApiKeyAuthUsecase(businessActions.size,this))
        kickoff()
    }

}