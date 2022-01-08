/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.nvilabs.sdk.ui.main

import androidx.annotation.Keep
import com.netacom.base.chat.base.BaseViewModel
import com.netacom.base.chat.base.StateModel
import com.toan_mobile.module.util.dispatchers.CoroutineDispatchers

/**
Created by vantoan on 15,Sep,2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Keep
class MainViewModel(private val coroutineDispatchers: CoroutineDispatchers) : BaseViewModel() {

    private val status = StateModel<Int>(0)

    fun test() {
        launchOnViewModelScope(coroutineDispatchers.io) {

        }
    }
}
