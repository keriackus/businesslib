import android.util.Log
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
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

    suspend fun execute() {
        val x = withContext(newSingleThreadContext("BINO")) { doTheJob() }
        Log.i("Execute: ", this::class.simpleName);
    }

    protected open suspend fun doTheJob() {
        Log.i("AsyncWork of: ", this::class.simpleName);
    }

    protected open suspend fun moveOn(e: Throwable?) {
        if (isStandalone()) {
            completionBlock?.invoke(e, businessResults)
        } else {
            if (e == null) {
                parentFeature?.continueExecution(this)
            } else {
                parentFeature?.onError(this, e)
            }
        }
    }

    constructor(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void) : this(
        0,
        null,
        completionBlock
    )
}