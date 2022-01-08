/*
 * *
 *  * Created by NivilabsTeam on 1/7/22, 4:14 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 1/7/22, 4:14 PM
 *
 */

package com.toan_mobile.module.data.model.api


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContactModel(
    @SerialName("address")
    val address: String? = "", // null
    @SerialName("createdAt")
    val createdAt: String? = "", // 2022-01-05T15:42:35.83927Z
    @SerialName("displayName")
    val displayName: String? = "", // talee4
    @SerialName("email")
    val email: String? = "", // null
    @SerialName("firstName")
    val firstName: String? = "", // null
    @SerialName("id")
    val id: Int? = 0, // 101
    @SerialName("lastName")
    val lastName: String? = "", // null
    @SerialName("ownerId")
    val ownerId: String? = "",
    @SerialName("phoneNumber")
    val phoneNumber: String? = "", // null
    @SerialName("type")
    val type: String? = "", // app
    @SerialName("updatedAt")
    val updatedAt: String? = "", // 2022-01-05T15:42:35.83927Z
    @SerialName("userId")
    val userId: String? = "" // 00014Dl6ff8qzw
)