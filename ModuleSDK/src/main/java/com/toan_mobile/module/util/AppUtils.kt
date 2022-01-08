/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.util

import android.util.Patterns


object AppUtils {
    private const val VN_PHONE_REGEX = "(0|[+]84)+([0-9]{9})\\b"
    private const val VN_MINI_PHONE_REGEX = "(0|[+]84)+([0-9]{0,9})\\b"
    private const val MENTION_REGEX = "@([0-9]){15,16}"
    private const val MENTION_ALL_REGEX = "@all"
    private const val EMAIL_REGEX = "([a-z][a-z0-9_.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2})\\b"

    fun getUrlFromMessage(message: String): String? {
        val matcherWeb = Patterns.WEB_URL.matcher(message)
        while (matcherWeb.find()) {
            var urlStr: String = matcherWeb.group()
            if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
                urlStr = urlStr.substring(1, urlStr.length - 1)
            }
            return urlStr
        }
        return null
    }
}
