package com.toan_mobile.module.data.model

class User {
    var id: String = ""
    var phone: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var displayName: String = ""
    var avatar: String = ""
    var userName: String = ""

    var isOnline = false

    val getDisplayName get() = "$firstName $lastName"

    constructor()

    constructor(id: String, userName: String, displayName: String)
}
