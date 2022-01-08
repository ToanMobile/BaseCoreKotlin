/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui

import android.os.Parcelable
import com.toan_mobile.module.data.model.ui.chat.NeMessage
import com.toan_mobile.module.util.getFileUrl
import kotlinx.parcelize.Parcelize

/**
Created by vantoan on 04/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Parcelize
data class NeGroup(
    var id: String? = null,
    var name: String? = null,
    var avatar: String? = null,
    var description: String? = null,
    var lastOnline: String? = null,
    var lastMessage: NeMessage? = null,
    var owner: NeUser? = null,
    var members: List<NeUser>? = null,
    var type: Int? = null,
    var createAt: String? = null,
    var updateAt: String? = null,
    var blockers: List<NeUser>? = null,
    var unReadCount: Int = 0,
    var isMute: Boolean = false,
    var isPin: Boolean = false,
    var isOnline: Boolean = false,
    // var partnerPublicKey: NePublicKey? = null,
    var timeOut: Int? = null,
    var pinListMessage: List<NeMessage>? = null,
    var isFcm: Boolean = false,
    var isDeletedContacts: Boolean = false,
    var administrators: List<NeUser>? = null,
    // var lastSosMessages: List<NeSosMessage>? = null,
    var isCallVideo: Boolean = false,
    var isMine: Long? = 0L,
    // var currentGroupCall: CallGroupAttachment? = null
) : Parcelable {

    fun setIsMine(userId: Long) {
        isMine = userId
    }

    val getDisplayAvatar: String?
        get() = avatar?.getFileUrl()

    val getDisplayName: String
        get() = if (name.isNullOrEmpty()) getNameMember() else name ?: ""

    private fun getNameMember(): String {
        var result = ""

        // find leader to display first
        /*result = members?.firstOrNull {
            it.id == owner?.id
        }?.let { leader ->
            if (leader.id == isMine) {
                String.format(StringUtils.getString(R.string.text_message_user_is_you, leader.getDisplayName))
            } else {
                leader.getDisplayName
            }
        } ?: ""

        if ((members?.size ?: 0) > 1 && result.isNotEmpty()) {
            result += ESCAPE_MEMBER_IN_GROUP_NAME
        }

        // append all member rest
        members?.filter { it.id != owner?.id }?.let { listMember ->
            listMember.forEachIndexed { index, user ->
                result += if (user.id == isMine) {
                    String.format(StringUtils.getString(R.string.text_message_user_is_you, user.getDisplayName))
                } else {
                    user.getDisplayName
                }

                if (index < listMember.lastIndex) {
                    result += ESCAPE_MEMBER_IN_GROUP_NAME
                }
            }
        }*/

        return result
    }

    fun getListMemberId(): List<Long?> = members?.map { it.id } ?: mutableListOf()

    fun getListAdministratorId(): List<Long?> = administrators?.map { it.id } ?: mutableListOf()
    override fun toString(): String {
        return "NeGroup(name=$name, avatar=$avatar, description=$description, lastMessage=$lastMessage, owner=$owner, type=$type, isMute=$isMute, isPin=$isPin)"
    }

    // fun isSecretChat() = type == GroupType.GROUP_TYPE_PRIVATE


}
