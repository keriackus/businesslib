import android.util.Log
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import makeable.intempus.domain.businesslib.core.Feature

import kotlin.collections.ArrayList

abstract class BusinessAction(
    private val parentFeature: Feature?
) {
    protected var completionBlock: ((error: Throwable?, objects: MutableList<Any>?) -> Void)? = null
    var actionOrder: Int = 0
    var businessResults = mutableListOf<Any>()
    private fun isStandalone(): Boolean {
        return parentFeature == null
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

    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Void) : this(
        null
    ){
        this.completionBlock = completionBlock
    }
}