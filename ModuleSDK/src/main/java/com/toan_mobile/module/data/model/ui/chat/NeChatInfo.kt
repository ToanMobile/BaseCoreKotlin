/*
 * *
 *  * Created by NivilabsTeam on 12/26/21, 1:56 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/9/21, 11:01 AM
 *
 */

package com.toan_mobile.module.data.model.ui.chat

import android.os.Parcelable
import com.netacom.base.chat.android_utils.StringUtils
import com.netacom.base.chat.util.isNotNull
import com.toan_mobile.module.R
import com.toan_mobile.module.data.model.ui.NeGroup
import com.toan_mobile.module.data.model.ui.NeUser
import kotlinx.parcelize.Parcelize

@Parcelize
data class NeChatInfo(
    var neUser: NeUser? = null,
    var neGroup: NeGroup? = null
) : Parcelable {

    constructor(neGroup: NeGroup) : this() {
        this.neGroup = neGroup
    }

    constructor(neUser: NeUser) : this() {
        this.neUser = neUser
    }

    val getDisplayName: String?
        get() = if (isGroup) neGroup?.getDisplayName else neUser?.getDisplayName

    val getDisplayAvatar: String?
        get() = if (isGroup) neGroup?.getDisplayAvatar else neUser?.getDisplayAvatar

    private val getStatusOnline: String
        get() = if (neUser?.isOnline == true) {
            StringUtils.getString(R.string.str_online_status)
        } else {
            StringUtils.getString(R.string.str_offline_status)
        }

    val isGroup: Boolean
        get() = neGroup.isNotNull

    val isOnline: Boolean
        get() = (neGroup?.isOnline ?: neUser?.isOnline) ?: false

    val phoneNumber: String?
        get() = neUser?.getPhone

    val getUserId: Long?
        get() = neUser?.id

    val getTotalMemberAndStatusOnline: String
        get() {
            return if (neGroup.isNotNull) "$totalMember ${StringUtils.getString(R.string.member)}"
            else getStatusOnline
        }

    private val totalMember: Int
        get() = neGroup?.members?.size ?: 0
}
