package makeable.intempus.domain.SignIn

import android.util.Log
import com.keriackus.thatwaseasy.businesslib.core.Feature
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignInFeature(
    completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit,
    var username: String,
    var password: String
) : Feature(completionBlock) {
    override fun prepareInitialBusinessActions() {
        addBusinessAction(
            BaseAuthUsecase(
                this,
                username,
                password
            )
        )
        addBusinessAction(
            ApiKeyAuthUsecase(
                this,
                username,
                password
            )
        )
    }
}