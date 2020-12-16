package makeable.intempus.domain

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.core.HttpConnectionUsecase

abstract class IntempusHttpConnectionUsecase : HttpConnectionUsecase {
    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Void) : super(
        completionBlock
    )

   override fun getHttpBaseUrl(): String {
        return "/https://intempus.dk/"
    }
}