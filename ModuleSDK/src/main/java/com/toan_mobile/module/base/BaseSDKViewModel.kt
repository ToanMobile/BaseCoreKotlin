/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.base

import com.netacom.base.chat.base.BaseViewModel
import com.toan_mobile.module.util.dispatchers.CoroutineDispatchers

/**Created by vantoan on 18/Sep/2020
Company: Netacom.
Email: hvtoan.dev@gmail.com
 **/

open class BaseSDKViewModel(
    private val coroutineDispatchers: CoroutineDispatchers
) : BaseViewModel() {

    val coroutineDispatcher get() = coroutineDispatchers
    private val isShowLog = true
}
