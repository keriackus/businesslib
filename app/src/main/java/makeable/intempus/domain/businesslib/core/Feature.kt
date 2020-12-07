package makeable.intempus.domain.businesslib.core

open class Feature(actionOrder: Int, parentFeature: Feature?) : BusinessAction(0, parentFeature) {
    var businessActions = mutableListOf<BusinessAction>();
    fun kickoff() {
        businessActions.first().execute()
    }

    fun continueExecution(coolBusinessAction: BusinessAction) {
        if (coolBusinessAction.actionOrder == businessActions.size) {
            moveOn(null)
        } else {
            businessActions[coolBusinessAction.actionOrder + 1].execute()
        }
    }

    fun onError(badBusinessAction: BusinessAction) {

    }
}