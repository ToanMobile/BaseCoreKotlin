/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.data.repository

import androidx.annotation.Keep
import com.netacom.base.chat.base.StateModel
import com.netacom.base.chat.network.BaseRepository
import com.toan_mobile.module.data.local.db.DbManager
import com.toan_mobile.module.data.local.prefs.StoreUtil
import com.toan_mobile.module.data.model.ui.contact.LocalContactInfo
import com.toan_mobile.module.data.model.ui.contact.NeContact
import com.toan_mobile.module.mapper.DB_UI_Mapper
import com.toan_mobile.module.network.socket.NvilabsServiceGrpcDataSource

@Keep
class ContactRepository(
    private val nvilabsServiceGrpcDataSource: NvilabsServiceGrpcDataSource,
    private val dbManager: DbManager,
    private val storeUtil: StoreUtil,
    private val dbUiMapper: DB_UI_Mapper
) : BaseRepository() {
    val contacts = StateModel(mutableListOf<NeContact>())
    val phonebookContacts = StateModel(mutableListOf<LocalContactInfo>())

    suspend fun createRoom(roomName: String,members: List<String>?) {
        nvilabsServiceGrpcDataSource.createRoom(name = roomName,members)
    }

   /* suspend fun register(username: String, password: String, displayName: String, sex: AccountProto.UserInfo.Sex): AccountProto.RegisterRes.Status{
        return nvilabsServiceGrpcDataSource.register(username,password,displayName,sex)
    }

    suspend fun addContact(contact: DbContactModel) {
        Logger.e("contacts111::${contacts.value()}")
        val listContact = contacts.value().filter { it._itemNeContact != null }.map {
            it._itemNeContact?._neContact!!
        }.toMutableList()
        listContact.add(dbUiMapper.contactToNeUser(contact))
        // Logger.e("contacts222::$listContact")
        contacts.update(ContactUtils.sortContacts(listContacts = listContact).toMutableList())
        // nvilabsServiceGrpcDataSource.addContact(contact)
        dbManager.saveContact(contact)
    }

    suspend fun getAllContacts() {
        val listContact = dbManager.getAllContacts().map { dbUiMapper.contactToNeUser(it) }
        val newList = ContactUtils.sortContacts(listContacts = listContact)
        Logger.e("newList::$newList")
        if (newList.isNotNull) {
            contacts.update(newList.toMutableList())
        }
    }

    suspend fun getAllPhoneBookContact(context: Context) {
        val listLocalContacts = mutableListOf<LocalContactInfo>()
        try {
            val listContactsName = context.getContactName()
            val listContactsNumber = context.getContactNumbers()
            var name: String? = null
            listContactsNumber?.forEach { map ->
                map.value.forEach { number ->
                    var phoneNumber = getOnlyDigits(number)
                    if (phoneNumber.length < 10) {
                        phoneNumber = "0$phoneNumber"
                    }
                    // convert phone to +84
                    phoneNumber.getPhoneWithCountryCode(context, "VI").let { _phoneNumber ->
                        if (listContactsName?.any { contact ->
                            name = contact.contactName
                            contact.id == map.key
                        } == true
                        ) {
                            val localContactInfo = LocalContactInfo(
                                contactName = name,
                                contactPhone = _phoneNumber,
                                contactNameUnaccent = if (name?.isEmpty() == true) "" else name?.unAccentText()
                            )
                            listLocalContacts.add(localContactInfo)
                        }
                    }
                }
            }
            Logger.e("listLocalContacts::$listLocalContacts")
            // TODO: convert về lại
            // listLocalContactsDB = contactRepository.getListLocalContactsDB(listLocalContacts)
            if (listLocalContacts.isNotEmpty()) {
                phonebookContacts.update(listLocalContacts)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
*/
    suspend fun convertFromPhoneContact(localContactInfo: LocalContactInfo) = dbUiMapper.localContactToNeUser(localContactInfo)

    /* suspend fun getUser(userStatus: UserStatus): NeUser? =
         userSkDbStatusMapper.mapToEntity(userStatus)?.let { dbContact -> dbManager.updatePartner(dbContact)?.let { userDbUiMapper.mapFromEntity(it) } }

     suspend fun getListUser(listUserStatus: List<UserStatus>): List<NeUser> {
         userSkDbStatusMapper.mapListUserStatus(listUserStatus).let { listContact ->
             dbManager.savePartnerInfoListToDb(listContact).let {
                 return userDbUiMapper.mapFromListContact(listContact)
             }
         }
     }*/
}
