package makeable.intempus.domain.businesslib.core

import BusinessAction

abstract class Feature: BusinessAction {
     var businessActions = mutableListOf<BusinessAction>()

    fun actionsCount() : Int {
        return businessActions.size;
    }
    override suspend fun doTheJob() {
        super.doTheJob()
        prepareInitialBusinessActions()
        businessActions.first().execute()
    }
     protected abstract fun prepareInitialBusinessActions()

     suspend fun continueExecution(coolBusinessAction: BusinessAction) {
        if (coolBusinessAction.actionOrder == businessActions.size) {
            moveOn(null)
        } else {
            businessActions[coolBusinessAction.actionOrder + 1].execute()
        }
        onSuccess(coolBusinessAction)
    }

  protected fun addBusinessAction(b : BusinessAction){
      businessActions.add(b)
  }
    open fun onSuccess(coolBusinessAction: BusinessAction){

    }
    open fun onError(badBusinessAction: BusinessAction,error: Throwable?) {
        completionBlock?.invoke(error, null)
    }
    constructor(actionOrder: Int, parentFeature: Feature) : super(actionOrder, parentFeature, null)
    constructor(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void) : super(completionBlock)
}
