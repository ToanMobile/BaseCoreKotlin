/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */
package com.toan_mobile.module.util


fun String?.getFileUrl(): String? {
    return this
    /*return when {
        this.isNullOrBlank() || this == ("${EndPoint.URL_CDN}/null") || this == ("${EndPoint.URL_CDN}/") || this == (EndPoint.URL_CDN) -> {
            null
        }
        this.startsWith(EndPoint.URL_CDN) -> {
            this
        }
        else -> {
            "${EndPoint.URL_CDN}/$this"
        }
    }*/
}

