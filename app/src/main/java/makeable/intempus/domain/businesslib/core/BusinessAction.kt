import android.util.Log
import makeable.intempus.domain.businesslib.core.Feature

import kotlin.collections.ArrayList

abstract class BusinessAction(
    var actionOrder: Int,
    private val parentFeature: Feature?,
    val completionBlock: ((error: Throwable?, objects: ArrayList<Object>?) -> Void)?
) {
    var businessResults = ArrayList<Object>()
    protected fun isStandalone(): Boolean {
        return parentFeature == null;
    }

    open fun execute() {
        Log.i("Execute: ", this::class.simpleName);
    }

    open fun moveOn(e: Throwable?) {
        if (isStandalone()) {
            completionBlock?.invoke(e, businessResults)
        } else {
            parentFeature?.moveOn(e)
        }
    }

    constructor(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void) : this(
        0,
        null,
        completionBlock
    )
}