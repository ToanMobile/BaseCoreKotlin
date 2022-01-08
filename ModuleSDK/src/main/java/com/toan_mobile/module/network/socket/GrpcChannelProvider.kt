/*
 * *
 *  * Created by NivilabsTeam on 12/16/21, 12:42 AM
 *  * Email: hvtoan.dev@gmail.com
 *  * Last modified 12/16/21, 12:42 AM
 *
 */

package com.toan_mobile.module.network.socket

import android.content.Context
import io.grpc.ManagedChannel
import io.grpc.Metadata
import io.grpc.android.AndroidChannelBuilder
import io.grpc.stub.MetadataUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

interface GrpcChannelProvider {
    fun channel(): ManagedChannel
}
const val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"

class GrpcAndroidChannelProvider(
    private val context: Context,
    private val address: String,
    private val port: Int
) : GrpcChannelProvider {

    private lateinit var _channel: ManagedChannel

    private val metadata = Metadata().apply {
        this.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer $token")
    }

    override fun channel(): ManagedChannel {
        if (!::_channel.isInitialized || _channel.isShutdown) {
            _channel = AndroidChannelBuilder
                .forAddress(address, port)
                .intercept(MetadataUtils.newAttachHeadersInterceptor(metadata))
                .context(context)
                .usePlaintext()
                .useTransportSecurity()
                .executor(Dispatchers.IO.asExecutor())
                .build()
        }
        return _channel
    }
}
