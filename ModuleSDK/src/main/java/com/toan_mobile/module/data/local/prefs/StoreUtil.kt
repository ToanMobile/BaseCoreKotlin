/*
 * *
 *  * Created by NivilabsTeam on 12/25/21, 7:41 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/25/21, 7:41 PM
 *
 */

package com.toan_mobile.module.data.local.prefs

class StoreUtil(private val dataStoreUtils: DataStoreUtils) {
    private val LOGGED_FLAG = "logged_flag"
    private val USER_INFO = "user_info"

    var isLogged: Boolean
        get() = dataStoreUtils.readBooleanData(LOGGED_FLAG, false)
        set(value) = dataStoreUtils.saveSyncBooleanData(LOGGED_FLAG, value = value)

    /*var userInfo: UserInfo?
        get() = dataStoreUtils.readStringData(USER_INFO).fromJson()
        set(value) = dataStoreUtils.saveSyncStringData(USER_INFO, value = value?.toJson() ?: "")

    fun onLogin(userInfo: UserInfo) {
        isLogged = true
        this.userInfo = userInfo
    }*/

    fun onLogOut() {
        isLogged = false
       // this.userInfo = null
    }
}