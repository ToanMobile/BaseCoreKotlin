/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.base

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.ViewDataBinding
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.anyPermanentlyDenied
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.netacom.base.chat.android_utils.AppUtils
import com.netacom.base.chat.android_utils.BarUtils
import com.netacom.base.chat.base.BaseViewModel
import com.netacom.base.chat.util.getThemeColor
import com.toan_mobile.module.R
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlin.reflect.KClass

abstract class BaseSDKDataFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    layoutID: Int,
    viewModelClass: KClass<VM>
) : BaseDataFragmentNew<DB, VM>(layoutID, viewModelClass) {

    private val requestCamera by lazy {
        permissionsBuilder(Manifest.permission.CAMERA).build()
    }

    private val requestRecord by lazy {
        permissionsBuilder(Manifest.permission.RECORD_AUDIO).build()
    }

    private val requestCallOutcome by lazy {
        permissionsBuilder(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO).build()
    }

    private val requestLocation by lazy {
        permissionsBuilder(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).build()
    }

    private val requestStorage by lazy {
        permissionsBuilder(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).build()
    }

    private val requestContact by lazy {
        permissionsBuilder(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS
        ).build()
    }

    private val requestStorageAndroidR = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initColorStatusBar()
        super.onViewCreated(view, savedInstanceState)
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    fun initColorStatusBar() {
        activity?.apply {
            BarUtils.setNavBarColor(this, getThemeColor(R.color.colorPrimary))
            BarUtils.setStatusBarLightMode(this, true)
        }
    }

    fun checkPermissionContact(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            requestContact.addListener {
                when {
                    it.allGranted() -> {
                        callbackSuccess()
                    }
                    it.anyPermanentlyDenied() -> {
                        showSnackBar(
                            R.string.permission_contact_denied,
                            R.string.permission_open_setting,
                            {
                                AppUtils.launchAppDetailsSettings()
                            }
                        )
                    }
                    else -> {
                        showSnackBar(R.string.permission_contact_denied)
                        callbackDenied()
                    }
                }
            }
            requestContact.send()
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    /*fun checkPermissionCamera(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            requestCamera.addListener {
                when {
                    it.allGranted() -> {
                        callbackSuccess()
                    }
                    it.anyPermanentlyDenied() -> {
                        showSnackBar(
                            R.string.permission_camera_denied,
                            R.string.permission_open_setting,
                            {
                                AppUtils.launchAppDetailsSettings()
                            }
                        )
                    }
                    else -> {
                        showSnackBar(R.string.permission_camera_denied)
                        callbackDenied()
                    }
                }
            }
            requestCamera.send()
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    fun checkPermissionRecordAudio(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            requestRecord.addListener {
                when {
                    it.allGranted() -> {
                        callbackSuccess()
                    }
                    it.anyPermanentlyDenied() -> {
                        showSnackBar(
                            R.string.permission_record_denied,
                            R.string.permission_open_setting,
                            {
                                AppUtils.launchAppDetailsSettings()
                            }
                        )
                    }
                    else -> {
                        showSnackBar(R.string.permission_record_denied)
                        callbackDenied()
                    }
                }
            }
            requestRecord.send()
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    fun checkPermissionCall(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            *//*NOTE : fix ALO-2300 , don't use global variable for requestCall
            , this will reflection wrong callback between BaseCoreCameraFragment and OutComeActivity *//*
            val requestCall = permissionsBuilder(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO).build()
            requestCall.addListener {
                when {
                    it.allGranted() -> {
                        callbackSuccess()
                    }
                    it.anyPermanentlyDenied() -> {
                        showSnackBar(
                            R.string.permission_camera_denied,
                            R.string.permission_open_setting,
                            {
                                AppUtils.launchAppDetailsSettings()
                            }
                        )
                    }
                    else -> {
                        showSnackBar(R.string.permission_camera_denied)
                        callbackDenied()
                    }
                }
            }
            requestCall.send()
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    fun checkPermissionStorage(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            *//*if (SDK_INT >= Build.VERSION_CODES.R) {
                if (!Environment.isExternalStorageManager()) {
                    DialogUtil.show(
                        context = requireContext(),
                        titleStr = getString(R.string.permission_storage_title),
                        messageStr = getString(R.string.permission_storage_desc),
                        cancelLabel = R.string.str_cancel,
                        okLabel = R.string.popup_confirm_logout_ok,
                        okFunc = {
                            try {
                                val intent =
                                    Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                                intent.addCategory("android.intent.category.DEFAULT")
                                intent.data = Uri.parse("package:${AppUtils.getAppPackageName()}")
                                requestStorageAndroidR.launch(intent)
                            } catch (e: java.lang.Exception) {
                                val intent = Intent()
                                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                                requestStorageAndroidR.launch(intent)
                            }
                        },
                        themeHelperImpl = themeHelperImpl
                    )
                } else {
                    callbackSuccess()
                }
            } else {
                requestStorage.addListener {
                    when {
                        it.allGranted() -> {
                            callbackSuccess()
                        }
                        it.anyPermanentlyDenied() -> {
                            showSnackBar(
                                R.string.permission_storage_denied, R.string.permission_open_setting,
                                {
                                    AppUtils.launchAppDetailsSettings()
                                }
                            )
                        }
                        else -> {
                            showSnackBar(R.string.permission_storage_denied)
                            callbackDenied()
                        }
                    }
                }
                requestStorage.send()
            }*//*
            requestStorage.addListener {
                when {
                    it.allGranted() -> {
                        callbackSuccess()
                    }
                    it.anyPermanentlyDenied() -> {
                        showSnackBar(
                            R.string.permission_storage_denied,
                            R.string.permission_open_setting,
                            {
                                AppUtils.launchAppDetailsSettings()
                            }
                        )
                    }
                    else -> {
                        showSnackBar(R.string.permission_storage_denied)
                        callbackDenied()
                    }
                }
            }
            requestStorage.send()
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }

    fun checkPermissionLocation(callbackSuccess: () -> Unit, callbackDenied: () -> Unit) {
        try {
            if (preferencesHelperImpl.nePermission?.isCheckLocation == true) {
                requestLocation.addListener {
                    when {
                        it.allGranted() -> {
                            callbackSuccess()
                        }
                        it.anyPermanentlyDenied() -> {
                            (preferencesHelperImpl.nePermission?.countLocation ?: 0) + 1
                            showSnackBar(
                                R.string.permission_location_denied,
                                R.string.permission_open_setting,
                                {
                                    AppUtils.launchAppDetailsSettings()
                                }
                            )
                        }
                        else -> {
                            (preferencesHelperImpl.nePermission?.countLocation ?: 0) + 1
                            showSnackBar(R.string.permission_location_denied)
                            callbackDenied()
                        }
                    }
                }
                requestLocation.send()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
        }
    }*/
}
