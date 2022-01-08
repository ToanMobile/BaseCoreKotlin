package com.toan_mobile.module.util

/**
 * Created by Tam Nguyen on 9/13/20.
 */
interface CallbackResult<T> {
    fun callBackSuccess(result: T)
    fun callBackError(error: String? = "")
}
