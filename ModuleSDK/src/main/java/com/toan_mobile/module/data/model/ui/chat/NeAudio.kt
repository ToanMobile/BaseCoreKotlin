/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.data.model.ui.chat

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
Created by vantoan on 03/Aug/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Parcelize
data class NeAudio(
    @Json(name = "url") var url: String? = null,
    @Json(name = "duration") var duration: Long? = null
) : Parcelable
