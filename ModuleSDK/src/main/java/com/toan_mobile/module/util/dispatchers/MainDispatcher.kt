/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.util.dispatchers

import com.netacom.base.chat.base.BroadcastEventBus
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainDispatcher<T>
@Inject constructor() : Dispatcher<T> {

    override val event = BroadcastEventBus<OneTimeEvent<T>>()

    override fun emit(value: T) {
        MainScope().launch {
            event.postEvent(OneTimeEvent(value))
        }
    }
}
