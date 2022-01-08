/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui.chat

import com.toan_mobile.module.data.model.ui.NeUser


class NeMentionPosition(
    val mentionId: String,
    var position: Int,
    var mentionName: String = "",
    var mentionUser: NeUser? = null,
    var isAll: Boolean = false
) {
    override fun toString(): String {
        return "NeMentionPosition(mention='$mentionId', position=$position, name='$mentionName')"
    }
}
