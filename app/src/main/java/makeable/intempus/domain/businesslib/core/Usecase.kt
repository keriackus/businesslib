package makeable.intempus.domain.businesslib.core

import BusinessAction

abstract class Usecase : BusinessAction {
    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Void) : super(completionBlock)
}