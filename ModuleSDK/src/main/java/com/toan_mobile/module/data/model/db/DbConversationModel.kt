package com.toan_mobile.module.data.model.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DbConversationModel : RealmObject() {
    @PrimaryKey
    var id: String = ""
}
