/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui.contact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalContactInfo(
    val id: String? = null,
    val contactName: String? = null,
    val contactPhone: String? = null,
    val contactNameUnaccent: String? = null
) : Parcelable {
    override fun toString(): String {
        return "LocalContactInfo(id=$id, contactName=$contactName, contactPhone=$contactPhone, contactNameUnaccent=$contactNameUnaccent)"
    }
    var isChecked = false
}
