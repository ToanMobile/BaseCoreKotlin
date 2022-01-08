/*
 * *
 *  * Created by NivilabsTeam on 12/19/21, 2:40 AM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/19/21, 2:38 AM
 *
 */

/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.util.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
Created by vantoan on 05/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

interface CoroutineDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class DefaultCoroutineDispatchers : CoroutineDispatchers {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}
