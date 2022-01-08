/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 9:55 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 9:55 PM
 *
 */

package com.toan_mobile.module.mapper

import com.toan_mobile.module.data.model.ui.NeUser
import com.toan_mobile.module.data.model.ui.contact.LocalContactInfo

class LocalContact_DB_UI_Mapper : EntityLiteMapper<LocalContactInfo, NeUser> {
    override fun mapToEntity(domain: LocalContactInfo): NeUser {
        return NeUser(
            firstName = domain.contactName,
            phones = listOf(domain.contactPhone ?: "")
        )
    }
}