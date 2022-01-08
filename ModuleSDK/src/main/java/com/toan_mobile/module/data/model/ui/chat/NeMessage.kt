/*
 * *
 *  * Created by NivilabsTeam on 12/26/21, 1:56 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/19/21, 4:18 PM
 *
 */

package com.toan_mobile.module.data.model.ui.chat

import android.os.Parcelable
import com.netacom.base.chat.android_utils.StringUtils
import com.toan_mobile.module.R
import com.toan_mobile.module.data.model.ui.NeUser
import kotlinx.parcelize.Parcelize

@Parcelize
data class NeMessage(
    var id: String? = null,
    val groupId: String? = null,
    var type: Int? = null,
    var content: String? = null,
    var attachment: NeAttachments? = null,
    val owner: NeUser? = null,
    var seen: List<NeUser>? = null,
    var createAt: Long = 0L,
    val updateAt: Long = 0L,
    var status: Long? = null,
    var pinnedAt: String? = null,
    var orderId: Long? = 0L,
    var senderName: String? = null,
    var senderAvatar: String? = null,
    var neTime: Long? = null,
    var version: String? = null,
    var messageUpdate: String? = null,
    var isEncrypted: Boolean = false,
    var timeOut: Int? = null,
    var isMentionAll: Boolean = false,
    var isGroupCallLastMessage: Boolean = false
) : Parcelable {

    val getDisplayName: String
        get() = senderName.orEmpty()

    val getDisplayContent: String
        get() = content ?: StringUtils.getString(R.string.blank)

    override fun toString(): String {
        return "NeMessage(id=$id, groupId=$groupId, type=$type, content=$content, owner=$owner, seen=$seen, createAt=$createAt, updateAt=$updateAt, " +
            "status=$status, senderName=$senderName, senderAvatar=$senderAvatar, neTime=$neTime, version=$version, messageUpdate=$messageUpdate, isEncrypted=$isEncrypted, " +
            "timeOut=$timeOut, isMentionAll=$isMentionAll, getDisplayName='$getDisplayName', getDisplayContent='$getDisplayContent')"
    }
}
