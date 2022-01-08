package com.toan_mobile.module.base

import androidx.databinding.ViewDataBinding
import com.netacom.base.chat.base.BaseDialogFragment
import com.netacom.base.chat.base.BaseViewModel

abstract class BaseSDKDialog<VM : BaseViewModel, DB : ViewDataBinding>(
    layoutID: Int,
    viewModelClass: Class<VM>
) : BaseDialogFragment<VM, DB>(layoutID, viewModelClass) {
/*
    @Inject
    lateinit var themeHelperImpl: ThemeHelperImpl

    @Inject
    lateinit var preferencesHelperImpl: PreferencesHelperImpl*/
}
