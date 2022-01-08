/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.netacom.base.chat.android_utils.BarUtils
import com.netacom.base.chat.base.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseSDKActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    layoutID: Int?,
    viewModelClass: KClass<VM>
) : BaseDataActivityNew<DB, VM>(layoutID, viewModelClass) {

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.transparentStatusBar(this)
        super.onCreate(savedInstanceState)
    }
}
