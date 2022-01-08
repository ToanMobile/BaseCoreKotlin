/*
 * *
 *  * Created by NivilabsTeam on 12/19/21, 3:09 PM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/19/21, 3:09 PM
 *
 */

package com.toan_mobile.module.network.socket

interface NvilabsServiceGrpcDataSource {
    suspend fun createRoom(name: String, members: List<String>?): String
}

class NvilabsServiceGrpcDataSourceImpl(
    private val chatServiceClient: ChatServiceClient,
) : NvilabsServiceGrpcDataSource {

    override suspend fun createRoom(name: String, members: List<String>?): String {
        return chatServiceClient.createRoom(name,members)
    }
}
