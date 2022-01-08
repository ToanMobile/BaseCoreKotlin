/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 9:55 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 9:55 PM
 *
 */

package com.toan_mobile.module.mapper

import com.toan_mobile.module.data.model.ui.chat.NeMessage
import com.toan_mobile.module.data.model.ui.chat.NeSubMessage

class NeMessageToNeSubMessage_Mapper : EntityLiteMapper<NeMessage, NeSubMessage> {
    override fun mapToEntity(domain: NeMessage): NeSubMessage {
        return NeSubMessage(

        )
    }
}