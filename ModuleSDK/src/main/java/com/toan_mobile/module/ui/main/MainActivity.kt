/*
  Created by NetaloTeamAndroid on 2020
 * Company: Netacom.

 */

package com.toan_mobile.module.ui.main

import android.os.Bundle
import androidx.annotation.Keep
import androidx.navigation.findNavController
import com.nvilabs.sdk.ui.main.MainViewModel
import com.toan_mobile.module.R
import com.toan_mobile.module.base.BaseSDKActivity
import com.toan_mobile.module.databinding.ActivitySdkBinding
import com.toan_mobile.module.util.extension.getEnumExtra

/**
Created by vantoan on 28,July,2021
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 */

@Keep
class MainActivity :
    BaseSDKActivity<ActivitySdkBinding, MainViewModel>(
        R.layout.activity_sdk,
        MainViewModel::class
    ) {
    companion object {
        const val FRAGMENT_DATA = "fragment_data"
    }

    private val navController by lazy {
        findNavController(R.id.root_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            it.getEnumExtra<FragmentType>()?.let { fragmentType ->
                when (fragmentType) {
                    FragmentType.ADD_CONTACT -> {}
                    FragmentType.CONTACT_DETAIL -> {}
                }
            }
        }
    }

    override fun initData() {
        viewModel.test()
    }

    override fun initViews() {

    }
}

enum class FragmentType {
    CONTACT_DETAIL, ADD_CONTACT
}
