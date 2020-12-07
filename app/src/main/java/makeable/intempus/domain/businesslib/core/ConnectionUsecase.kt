package makeable.intempus.domain.businesslib.core

import makeable.intempus.domain.businesslib.data.http.HttpEndpoint

abstract class ConnectionUsecase(
    actionOrder: Int,
    parentFeature: Feature?
) : BusinessAction(actionOrder, parentFeature) {

    abstract fun doConnection()
    abstract fun getEndpoint(): HttpEndpoint
    abstract fun getServiceParams(): List<String>
    override fun execute() {
        super.execute()
        doConnection()
    }

}