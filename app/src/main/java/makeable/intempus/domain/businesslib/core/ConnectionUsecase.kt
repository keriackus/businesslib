package makeable.intempus.domain.businesslib.core

import BusinessAction
import makeable.intempus.domain.businesslib.data.http.HttpEndpoint
import makeable.intempus.domain.businesslib.data.http.HttpServiceParameter

abstract class ConnectionUsecase : BusinessAction {

    abstract fun doConnection()
    abstract fun getEndpoint(): HttpEndpoint
    abstract fun getServiceParams(): List<HttpServiceParameter>
    override fun execute() {
        super.execute()
        doConnection()
    }
    constructor(actionOrder: Int, parentFeature: Feature) : super(actionOrder, parentFeature, null)
    constructor(completionBlock: (error: Throwable?, objects: ArrayList<Object>?) -> Void) : super(completionBlock)
}