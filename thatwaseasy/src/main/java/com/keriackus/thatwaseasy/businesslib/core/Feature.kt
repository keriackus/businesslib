package com.keriackus.thatwaseasy.businesslib.core

import BusinessAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class Feature : BusinessAction {
    private var businessActions = mutableListOf<BusinessAction>()

    fun actionsCount(): Int {
        return businessActions.size;
    }

    override  fun doTheJob() {
        super.doTheJob()
        prepareInitialBusinessActions()
       getCoroutineJob().launch {
            businessActions.first().doTheJob()
        }
    }

    suspend fun x(){
        
    }
    protected abstract fun prepareInitialBusinessActions()

     fun continueExecution(coolBusinessAction: BusinessAction) {
        if (coolBusinessAction.actionOrder == businessActions.size) {
            moveOn(null)
        } else {
            businessActions[coolBusinessAction.actionOrder + 1].execute()
        }
        onSuccess(coolBusinessAction)
    }

    protected fun addBusinessAction(b: BusinessAction) {
        b.actionOrder = businessActions.size
        businessActions.add(b)

    }

    open fun onSuccess(coolBusinessAction: BusinessAction) {
    }

    open fun onError(badBusinessAction: BusinessAction, error: Throwable?) {
        completionBlock?.invoke(error, null)
    }

    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit) : super(
        completionBlock
    )
}
