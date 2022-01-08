/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
@file:Suppress("RedundantSuspendModifier")

package com.toan_mobile.module.data.local.db

import com.toan_mobile.module.data.model.db.DbContactModel
import com.toan_mobile.module.util.extension.allSafe
import io.realm.Realm

class DbManager() {
    private val TAG = "__ReamlDBManager"
    private val isShowLog = false

    fun saveListContact(listContact: List<DbContactModel>) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            listContact.forEach { contact ->
                realm.insertOrUpdate(contact)
            }
        }
    }

    suspend fun saveContact(contact: DbContactModel) {
        Realm.getDefaultInstance().executeTransaction { realm ->
            val currentIdNum: Number? = realm.where(DbContactModel::class.java).max(DbContactModel::id.name)
            val nextId = if (currentIdNum == null || currentIdNum.toLong() == 0L) {
                1
            } else {
                currentIdNum.toLong() + 1
            }
            contact.id = nextId
            realm.insertOrUpdate(contact)
        }
    }

    suspend fun getAllContacts() = Realm.getDefaultInstance().use { realm -> realm.where(
        DbContactModel::class.java).allSafe() }

    fun refreshDatabase() {
        Realm.getDefaultInstance().use {
            it.refresh()
        }
    }
}
