/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui

import android.os.Parcelable
import androidx.annotation.Keep
import com.netacom.base.chat.R
import com.netacom.base.chat.android_utils.StringUtils
import com.netacom.base.chat.util.TimeUtils
import com.toan_mobile.module.util.getFileUrl
import kotlinx.parcelize.Parcelize

/**
Created by vantoan on 04/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Keep
@Parcelize
data class NeUser(
    var id: Long? = 0L,
    var firstName: String? = "",
    var lastName: String? = "",
    var username: String? = null,
    var avatar: String? = null,
    var phones: List<String> = mutableListOf(),
    var old: Int? = null,
    var sex: Int? = null,
    var address: String? = null,
    var token: String? = null,
    var isOnline: Boolean? = false,
    var status: String? = null,
    var email: String? = null,
    var colorInGroup: Int? = null,
    val isRegistered: Boolean? = false,
    var isBlocked: Boolean = false,
    var isDeleted: Boolean = false,
    var lastSeenAt: Long? = 0L,
    var isChecked:Boolean = false
) : Parcelable {

    private val fullName: String?
        get() = if (firstName?.isNotEmpty() == true)
            if (lastName?.isNotEmpty() == true)
                "$firstName $lastName"
            else firstName
        else
            lastName

    val getDisplayName: String
        get() = fullName ?: getPhone ?: ""

    val getDisplayAvatar: String?
        get() = avatar?.getFileUrl()

    val getPhone: String
        get() = if (phones.isNotEmpty()) phones[0] else ""

    val getStatusOnline: String
        get() = if (isOnline == true) {
            StringUtils.getString(R.string.str_online_status)
        } else {
            StringUtils.getString(R.string.str_offline_status)
        }

    val getContactOnlineStatus: String
        get() = if (isOnline == true) {
            StringUtils.getString(R.string.str_online_status)
        } else {
            lastSeenAt?.let {
                String.format(
                    StringUtils.getString(R.string.text_online_status_time),
                    TimeUtils.parseTimeAgo(it)
                )
            } ?: StringUtils.getString(R.string.str_offline_status)
        }

    override fun toString(): String {
        return "NeUser(id=$id, token=$token, getDisplayName='$getDisplayName',  getDisplayAvatar=$getDisplayAvatar, getPhone='$getPhone', getStatusOnline='$getStatusOnline', isDeleted = '$isDeleted', lastSeenAt='$lastSeenAt')"
    }
}

@Parcelize
class NeUsers: ArrayList<NeUser>(), Parcelable
