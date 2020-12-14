package makeable.intempus.domain.businesslib.core

import BusinessAction

abstract class Feature: BusinessAction {
    var businessActions = mutableListOf<BusinessAction>()
    override fun execute() {
        super.execute()
        prepareInitialBusinessActions()
        businessActions.first().execute()
    }
     protected abstract fun prepareInitialBusinessActions()

    fun continueExecution(coolBusinessAction: BusinessAction) {
        if (coolBusinessAction.actionOrder == businessActions.size) {
            moveOn(null)
        } else {
            businessActions[coolBusinessAction.actionOrder + 1].execute()
        }
    }

    fun onError(badBusinessAction: BusinessAction,error: Throwable?) {
        completionBlock?.invoke(error, null)
    }
    constructor(actionOrder: Int, parentFeature: Feature) : super(actionOrder, parentFeature, null)
    constructor(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void) : super(completionBlock)
}