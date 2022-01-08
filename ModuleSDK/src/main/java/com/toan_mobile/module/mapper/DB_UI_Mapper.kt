/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 9:55 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 9:55 PM
 *
 */

package com.toan_mobile.module.mapper

import com.toan_mobile.module.data.model.db.DbContactModel
import com.toan_mobile.module.data.model.ui.NeUser
import com.toan_mobile.module.data.model.ui.contact.LocalContactInfo

class DB_UI_Mapper(
    private val contactDbUiMapper: Contact_DB_UI_Mapper,
    private val localContactDbUiMapper: LocalContact_DB_UI_Mapper,
) {
    fun contactToNeUser(contactModel: DbContactModel) : NeUser = contactDbUiMapper.mapToEntity(contactModel)

    fun listContactToListNeUser(listContact: List<DbContactModel>) : List<NeUser> = contactDbUiMapper.mapToListEntity(listContact)

   // fun neContactToNeUser(neContact: NeContact) : NeUser = contactDbUiMapper.mapToEntity(neContact)

    fun localContactToNeUser(localContactInfo: LocalContactInfo) : NeUser = localContactDbUiMapper.mapToEntity(localContactInfo)
}