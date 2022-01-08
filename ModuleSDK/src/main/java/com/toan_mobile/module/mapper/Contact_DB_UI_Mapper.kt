/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 7:49 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 7:49 PM
 *
 */

package com.toan_mobile.module.mapper

import com.toan_mobile.module.data.local.db.DbManager
import com.toan_mobile.module.data.model.db.DbContactModel
import com.toan_mobile.module.data.model.ui.NeUser

class Contact_DB_UI_Mapper(
    private val dbManager: DbManager
) : EntityLiteMapper<DbContactModel, NeUser> {

    override fun mapToEntity(domain: DbContactModel): NeUser {
        return NeUser(
            id = domain.id,
            firstName = domain.firstName,
            lastName = domain.lastName,
            phones = domain.phones.map { it },
            avatar = domain.avatar,
            isOnline = true, //dbManager.getPartnerInfoById(entity.contactId)?.contactStatus,
            username = if (domain.userName.isNotEmpty()) domain.userName else "${domain.firstName}${domain.lastName}"
        )
    }
}
