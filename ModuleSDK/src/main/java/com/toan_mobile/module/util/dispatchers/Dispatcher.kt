/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.util.dispatchers

import androidx.annotation.Keep
import com.netacom.base.chat.base.BroadcastEventBus

@Keep
interface Dispatcher<T> {

    val event: BroadcastEventBus<OneTimeEvent<T>>

    fun emit(value: T)
}
