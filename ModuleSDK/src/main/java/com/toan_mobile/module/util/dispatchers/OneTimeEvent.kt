/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.util.dispatchers

import java.util.concurrent.atomic.AtomicBoolean

class OneTimeEvent<T>(
    private val value: T
) {

    private val isConsumed = AtomicBoolean(false)

    private fun getValue(): T? =
        if (isConsumed.compareAndSet(false, true)) value
        else null

    fun consume(block: (T) -> Unit): T? =
        getValue()?.also(block)
}
