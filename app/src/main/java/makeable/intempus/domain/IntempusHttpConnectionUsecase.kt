package makeable.intempus.domain

import com.keriackus.thatwaseasy.businesslib.core.Feature
import com.keriackus.thatwaseasy.businesslib.core.HttpConnectionUsecase

abstract class IntempusHttpConnectionUsecase : HttpConnectionUsecase {
    constructor(parentFeature: Feature) : super(parentFeature)

   override fun getHttpBaseUrl(): String {
        return "/https://intempus.dk/"
    }
}