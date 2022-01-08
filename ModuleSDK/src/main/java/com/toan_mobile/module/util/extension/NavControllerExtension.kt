/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.util.extension

import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun NavController.navigateIfSafe(navDirections: NavDirections) {
    currentDestination?.getAction(navDirections.actionId)?.let {
        navigate(navDirections)
    }
}

fun NavController.navigateIfSafeClearStack(navDirections: NavDirections) {
    currentDestination?.getAction(navDirections.actionId)?.let {
        navigate(navDirections)
        popBackStack()
    }
}

fun NavController.navigateIfSafe(
    @IdRes actionId: Int,
    vararg params: Pair<String, Any?>,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    currentDestination?.getAction(actionId)?.let {
        navigate(actionId, bundleOf(*params), navOptions, navExtras)
    }
}
