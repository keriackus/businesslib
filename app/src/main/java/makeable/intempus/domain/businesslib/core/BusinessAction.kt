package makeable.intempus.domain.businesslib.core

import android.util.Log

abstract class BusinessAction(val actionOrder: Int, val parentFeature: Feature?) {

    protected fun isStandalone(): Boolean {
        return parentFeature == null;
    }

    open fun execute() {
        Log.i("Execute: ", this::class.simpleName);
    }

    open fun moveOn(e: Throwable?) {
        if (parentFeature != null) {
            if (e == null) {
                parentFeature.continueExecution(this)
            } else {
                parentFeature.onError(this)
            }
            parentFeature.moveOn(e)
        } else {

        }
    }
}