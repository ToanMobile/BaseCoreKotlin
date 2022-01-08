package com.toan_mobile.module.ui.profile

import com.toan_mobile.module.R
import com.toan_mobile.module.base.BaseSDKDataFragment
import com.toan_mobile.module.databinding.FragmentProfileBinding
import com.toan_mobile.module.ui.call.ProfileViewModel

class ProfileFragment : BaseSDKDataFragment<FragmentProfileBinding, ProfileViewModel>(
    R.layout.fragment_profile,
    ProfileViewModel::class
) {

    override fun initData() {
    }

    override fun initViews() {
    }

    override fun setupTheme() {
    }

    override fun syncEvent() {
    }
}
