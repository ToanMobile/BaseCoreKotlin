package com.toan_mobile.module.data.model.db

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class DbContactModel : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var phones: RealmList<String> = RealmList()
    var firstName: String = ""
    var lastName: String = ""
    var avatar: String = ""
    var userName: String = ""

    @Ignore
    var isOnline = false

    val getDisplayName get() = "$firstName $lastName"
    override fun toString(): String {
        return "ContactModel(phone='$phones', avatar='$avatar', userName='$userName', getDisplayName='$getDisplayName')"
    }
}
