package com.toan_mobile.module.data.intercepter

import android.util.Log
import io.grpc.*
import java.util.concurrent.TimeUnit

class LoggingInterceptor : ClientInterceptor {
    val TAG = "Log Api GRPC"
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {
        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            next.newCall(method, callOptions.withDeadlineAfter(10, TimeUnit.SECONDS))
        ) {
            override fun sendMessage(message: ReqT) {
                Log.i(TAG, "---------------------------------------------------------------------------------------------------------------------------\n")
                Log.i(
                    TAG,
                    method.fullMethodName.toString() + " --- request content: \n" +
                        message.toString()
                )
                Log.i(TAG, "---------------------------------------------------------------------------------------------------------------------------\n")
                super.sendMessage(message)
            }

            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                val listener: Listener<RespT> = object : Listener<RespT>() {
                    override fun onMessage(message: RespT) {
                        Log.i(TAG, "---------------------------------------------------------------------------------------------------------------------------\n")
                        Log.i(
                            TAG,
                            method.fullMethodName
                                .toString() + " --- response content: \n" +
                                message.toString()
                        )
                        Log.i(TAG, "---------------------------------------------------------------------------------------------------------------------------\n")
                        super.onMessage(message)
                    }
                }
                super.start(listener, headers)
            }
        }
    }
}
