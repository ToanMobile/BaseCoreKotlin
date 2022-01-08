/*
 * *Created by NvilabsTeamAndroid on 2021
 * Company: Nvilabs.
 *  *
 */
package com.toan_mobile.module.core

import androidx.annotation.Keep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
Created by ToanMobile on 02,DEC,2021
Company: Nvilabs.
Email: hvtoan.dev@gmail.com
 */

@Keep
@Singleton
class NvilabsSDKCore : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}
