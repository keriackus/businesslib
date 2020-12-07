package makeable.intempus.domain.usecases

import makeable.intempus.domain.businesslib.core.ConnectionUsecase
import makeable.intempus.domain.businesslib.core.Feature

abstract class BaseConnectionUsecase(actionOrder: Int,
                                     parentFeature: Feature
) : ConnectionUsecase(actionOrder, parentFeature) {
    override fun doConnection() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getServiceParams(): List<String> {
        TODO("Not yet implemented")
    }
}