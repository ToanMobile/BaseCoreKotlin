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
data class NeAttachments(
    @Json(name = "images")
    var images: List<NeImage>? = null,
    var call: CallAttachment? = null,
    var callGroup: CallGroupAttachment? = null,
    var stickerUrl: String? = null,
    @Json(name = "audio")
    var audio: NeAudio? = null,
    @Json(name = "video")
    var video: NeVideo? = null,
    @Json(name = "file")
    var file: NeDocument? = null,
    @Json(name = "reply_message")
    var replyMessage: NeMessage? = null,
    @Json(name = "forward_message")
    var forwardMessage: NeMessage? = null,
    @Json(name = "updated_group_avatar")
    var updatedGroupAvatar: String? = null,
    @Json(name = "updated_group_name")
    var updatedGroupName: String? = null,
    @Json(name = "added_uins")
    var addedUins: List<String>? = null,
    @Json(name = "removed_uin")
    var removedUin: String? = null,
    @Json(name = "push_all")
    var pushAllUins: List<String>? = null,
    @Json(name = "type")
    var type: String? = null,
    @Json(name = "owner_uin")
    var ownerUin: String? = null,
    @Json(name = "sticker")
    var stickerId: String? = null,
    @Json(name = "location")
    var location: NeLocation? = null,
    @Json(name = "pin_message")
    var pin_message: NeMessage? = null,
    @Json(name = "unpin_message")
    var unpin_message: NeMessage? = null,
    @Json(name = "unpin_all_messages")
    var unpin_all_message: Boolean? = null,
    @Json(name = "live_location")
    var liveLocation: NeLiveLocation? = null,
    @Json(name = "added_admins")
    var addedAdminUins: List<String>? = null,
    @Json(name = "deleted_admins")
    var removedAdminUin: List<String>? = null,
    @Json(name = "updated_message_delete_timer")
    var updated_message_delete_timer: Int? = null,
    // ForStickerMarket
    @Json(name = "sticker_server_id")
    var stickerCategoryServerId: Int? = null,
    @Json(name = "sticker_category_name")
    var stickerCategoryName: String? = null
) : Parcelable {
    override fun toString(): String {
        return "NeAttachments(images=$images, call=$call, callGroup=$callGroup, stickerUrl=$stickerUrl, audio=$audio, video=$video, file=$file, replyMessage=$replyMessage, " +
            "forwardMessage=$forwardMessage, updatedGroupAvatar=$updatedGroupAvatar, updatedGroupName=$updatedGroupName, addedUins=$addedUins, " +
            "removedUin=$removedUin, type=$type, ownerUin=$ownerUin, stickerId=$stickerId, location=$location, pin_message=$pin_message," +
            " unpin_message=$unpin_message, unpin_all_message=$unpin_all_message, updated_message_delete_timer=$updated_message_delete_timer)"
    }
}

@Parcelize
data class CallAttachment(
    @Json(name = "call_id")
    var callId: String? = null,
    @Json(name = "group_id")
    var groupId: String? = null,
    @Json(name = "call_type")
    var callType: Int? = null,
    @Json(name = "media_type")
    var mediaType: Int? = null,
    @Json(name = "call_status")
    var callStatus: Int? = null,
    @Json(name = "caller_uin")
    var callerUin: String? = null,
    @Json(name = "callee_uins")
    var calleeUins: List<String>? = null,
    @Json(name = "accepted_uins")
    var acceptedUins: List<String>? = null,
    @Json(name = "started_at")
    var startedAt: String? = null,
    @Json(name = "stopped_at")
    var stoppedAt: String? = null,
    @Json(name = "accepted_at")
    var acceptedAt: String? = null,
    @Json(name = "connected_at")
    var connectedAt: String? = null
) : Parcelable {
    override fun toString(): String {
        return "CallAttachment(callId=$callId, groupId=$groupId, callType=$callType, mediaType=$mediaType, callStatus=$callStatus, callerUin=$callerUin, calleeUins=$calleeUins, " +
            "acceptedUins=$acceptedUins, startedAt=$startedAt, stoppedAt=$stoppedAt, acceptedAt=$acceptedAt, connectedAt=$connectedAt)"
    }
}

@Parcelize
data class CallGroupAttachment(
    @Json(name = "groupcall_id")
    var groupCallId: String? = null,
    @Json(name = "message_id")
    var messageId: String? = null,
    @Json(name = "group_id")
    var groupId: String? = null,
    @Json(name = "status")
    var status: Int? = null,
    @Json(name = "type")
    var mediaType: Int? = null,
    @Json(name = "sender_uin")
    var senderUin: String? = null,
    @Json(name = "created_at")
    var createdAt: String? = null,
    @Json(name = "updated_at")
    var updatedAt: String? = null,
    @Json(name = "stopped_at")
    var stoppedAt: String? = null,
    @Json(name = "secret_id")
    var secretId: String? = null,
    @Json(name = "private_key")
    var privateKey: String? = null,
    @Json(name = "public_key")
    var publicKey: String? = null,
    @Json(name = "invited_uins")
    var invitedUins: List<String>? = null,
    @Json(name = "joined_uins")
    var joinedUins: List<String>? = null,
    @Json(name = "active_uins")
    var activeUins: List<String>? = null,
) : Parcelable {
    override fun toString(): String {
        return "CallGroupAttachment(groupCallId=$groupCallId, messageId=$messageId, groupId=$groupId, status=$status, mediaType=$mediaType, " +
            "senderUin=$senderUin, createdAt=$createdAt, updatedAt=$updatedAt, stoppedAt=$stoppedAt, secretId=$secretId, privateKey=$privateKey, " +
            "publicKey=$publicKey, invitedUins=$invitedUins, joinedUins=$joinedUins, activeUins=$activeUins)"
    }
}

@Parcelize
data class NeVideo(
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "duration")
    var duration: Long? = null,
    @Json(name = "thumbnail_url")
    var thumbnailUrl: String? = null,
    @Json(name = "width")
    var width: Int = 0,
    @Json(name = "height")
    var height: Int = 0,
    @Json(name = "progress")
    var progress: String? = null,
    @Json(name = "compress")
    var compress: String? = null,
    @Json(name = "compressId")
    var compressId: String? = null,
    @Json(name = "uploadId")
    var uploadId: String? = null,
    @Json(name = "mergeId")
    var mergeId: String? = null
) : Parcelable {
    override fun toString(): String {
        return "NeVideo(url=$url, duration=$duration, thumbnailUrl=$thumbnailUrl, width=$width, height=$height, progress=$progress, " +
            "compress=$compress, compressId=$compressId, uploadId=$uploadId, mergeId=$mergeId)"
    }
}

@Parcelize
data class NeDocument(
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "file_name")
    var name: String? = null,
    @Json(name = "file_extension")
    var extension: String? = null,
    @Json(name = "size")
    var size: Long? = null,
    @Json(name = "sizeText")
    var sizeText: String? = null
) : Parcelable {
    override fun toString(): String {
        return "NeDocument(url=$url, name=$name, extension=$extension, size=$size, sizeText=$sizeText)"
    }
}

@Parcelize
data class NeLocation(
    @Json(name = "lat")
    var lat: Double? = null,
    @Json(name = "long")
    var long: Double? = null,
    @Json(name = "image_url")
    var image_url: String? = null,
    @Json(name = "address")
    var address: String? = null,
    @Json(name = "width")
    var width: Int = 0,
    @Json(name = "height")
    var height: Int = 0
) : Parcelable {
    override fun toString(): String {
        return "NeLocation(lat=$lat, long=$long, image_url=$image_url, address=$address)"
    }
}

@Parcelize
data class NeLiveLocation(
    @Json(name = "message_id")
    var messageId: String? = null,
    @Json(name = "group_id")
    var groupId: String? = null,
    @Json(name = "duration")
    var durationLiveLocation: String? = null,
    @Json(name = "started_at")
    var startTimeLocation: String? = null,
    @Json(name = "ended_at")
    var endTimeLocation: String? = null,
    @Json(name = "updated_at")
    var updatedTimeLocation: String? = null,
    @Json(name = "image_url")
    var imageUrl: String? = null,
    @Json(name = "latitude")
    var latitude: String? = null,
    @Json(name = "longitude")
    var longitude: String? = null,
    @Json(name = "altitude")
    var altitude: String? = null,
    @Json(name = "address")
    var address: String? = null,
    @Json(name = "status")
    var statusLiveLocation: Int? = null,
    @Json(name = "is_sos")
    var isSos: Boolean? = null
) : Parcelable {
    override fun toString(): String {
        return "NeLiveLocation(lat=$latitude, long=$longitude, image_url=$imageUrl, address=$address)"
    }
}
