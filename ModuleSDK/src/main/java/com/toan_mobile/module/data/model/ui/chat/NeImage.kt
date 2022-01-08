package com.toan_mobile.module.data.model.ui.chat

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
Created by vantoan on 28/Sep/2020
Company: Netacom.
Email: huynhvantoan.itc@gmail.com
 **/

@Parcelize
class NeImage(
    @Json(name = "sub_index")
    var sub_index: Int? = null,
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "width")
    var width: Int? = null,
    @Json(name = "height")
    var height: Int? = null,
    @Json(name = "index")
    var index: Int = 0,
    @Json(name = "isLoading")
    var isLoading: Boolean = false,
    @Json(name = "compress")
    var compress: Boolean? = false,
    @Json(name = "progress")
    var progress: String? = null
) : Parcelable {
    override fun toString(): String {
        return "NeImage(sub_index=$sub_index, url=$url, width=$width, height=$height, index=$index, isLoading=$isLoading, compress=$compress, progress=$progress)"
    }
}
