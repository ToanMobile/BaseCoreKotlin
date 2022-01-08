/*
 * *
 *  * Created by NivilabsTeam on 12/16/21, 12:41 AM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/16/21, 12:41 AM
 *
 */

package com.toan_mobile.module.network.socket

interface AuthServiceClient {
    suspend fun getConfig(): String
}

class AuthServiceClientImpl(private val channelProvider: GrpcChannelProvider) : AuthServiceClient {

    override suspend fun getConfig(): String {
        return try {
           /* val stub = AccountGrpcKt.AccountCoroutineStub(channelProvider.channel())
            val request: AccountProto.GetConfigReq = AccountProto.GetConfigReq.newBuilder().build()
            val response: AccountProto.GetConfigRes = stub.getConfig(request)
            response.toString()*/
            ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
