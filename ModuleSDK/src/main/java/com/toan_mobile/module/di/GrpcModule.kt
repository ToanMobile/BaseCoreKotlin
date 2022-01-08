package com.toan_mobile.module.di

import com.toan_mobile.module.network.socket.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val PROPERTY_GRPC_SERVER_ADDRESS_KEY = "grpcServerAddress"
const val PROPERTY_GRPC_SERVER_PORT_KEY = "grpcServerPort"

val grpcModule = module {
    single<GrpcChannelProvider> {
        GrpcAndroidChannelProvider(
            androidContext(),
            getProperty(PROPERTY_GRPC_SERVER_ADDRESS_KEY),
            (getProperty(PROPERTY_GRPC_SERVER_PORT_KEY) as String).toInt()
        )
    }

    single<ChatServiceClient> { ChatServiceClientImpl(get()) }

    single<AuthServiceClient> { AuthServiceClientImpl(get()) }

    //Core Use
    single<NvilabsServiceGrpcDataSource> { NvilabsServiceGrpcDataSourceImpl(get()) }
}
