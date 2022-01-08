/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui.chat

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
Created by vantoan on 03/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Parcelize
data class NeSubMessage(
    var neMessage: NeMessage? = null,
    var neTime: Long? = null
) : Parcelable {

    val isMessage
        get() = neMessage != null && neTime == null

    val isInValid
        get() = neMessage == null && neTime == null

    override fun toString(): String {
        return "NeSubMessage(neMessage=$neMessage, neTime=$neTime)"
    }
}
