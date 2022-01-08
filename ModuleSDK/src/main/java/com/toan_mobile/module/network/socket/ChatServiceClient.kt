/*
 * *
 *  * Created by NivilabsTeam on 12/16/21, 12:41 AM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/16/21, 12:41 AM
 *
 */

package com.toan_mobile.module.network.socket

import com.toan_mobile.module.data.model.db.DbContactModel

interface ChatServiceClient {
    suspend fun createRoom(name: String, members: List<String>?): String
    suspend fun addContact(contact: DbContactModel): String
}

class ChatServiceClientImpl(private val channelProvider: GrpcChannelProvider) : ChatServiceClient {

    override suspend fun createRoom(name: String, members: List<String>?): String {
        return try {
            /*val stub = ChatApiGrpcKt.ChatApiCoroutineStub(channelProvider.channel())
            val request: ChatProto.CreateRoomReq =
                ChatProto.CreateRoomReq.newBuilder().setName(name).addAllMembers(members).build()
            Logger.e(request.toString())
            val response: ChatProto.CreateRoomRes = stub.createRoom(request)
            Logger.e(response.toString())
            response.toString()*/
            ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

//    suspend fun querySpaces(){
//        try {
//            val stub = ChatApiGrpcKt.ChatApiCoroutineStub(channelProvider.channel())
//            val request = ChatProto.Que
//        }catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    override suspend fun addContact(contact: DbContactModel): String {
        return ""
    }
}
