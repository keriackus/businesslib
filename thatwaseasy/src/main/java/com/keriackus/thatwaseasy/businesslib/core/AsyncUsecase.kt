package com.keriackus.thatwaseasy.businesslib.core

abstract class AsyncUsecase : Usecase {
    constructor(parentFeature: Feature) : super(parentFeature)
    constructor(completionBlock: (error: Throwable?, objects: MutableList<Any>?) -> Unit) : super(completionBlock)


}