package makeable.intempus.domain.usecases

import makeable.intempus.domain.businesslib.core.Feature
import makeable.intempus.domain.businesslib.data.http.HttpEndpoint

class BaseAuthUsecase(actionOrder: Int, parentFeature: Feature) : BaseConnectionUsecase(actionOrder,
    parentFeature) {

    override fun execute() {
        super.execute()
        doConnection()
    }

    override fun getEndpoint(): HttpEndpoint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServiceParams(): List<String> {
        TODO("Not yet implemented")
    }
}