package com.toan_mobile.module.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.netacom.base.chat.app.isShowLogHTTP
import com.netacom.base.chat.network.logging.Level
import com.netacom.base.chat.network.logging.LoggingInterceptor
import com.netacom.base.chat.okhttp.clearText
import com.netacom.base.chat.util.isNotNull
import com.toan_mobile.module.data.intercepter.HostApiInterceptor
import com.toan_mobile.module.data.local.prefs.DataStoreUtils
import com.toan_mobile.module.network.remote.ApiService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://api.test.org/"
private const val BASE_URL_UPLOAD = "http://api.test.org/"
private const val OKHttpApi = "OKHttpApi"
private const val OKHttpUpload = "OKHttpUpload"

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {

    factory {
        with(LoggingInterceptor.Builder()) {
            loggable(isShowLogHTTP)
            setLevel(Level.BODY)
            log(Platform.INFO)
            request("App_request")
            response("App_response")
            build()
        }
    }

    factory { HostApiInterceptor(get()) }

    factory(named(OKHttpApi)) {
        with(OkHttpClient.Builder()) {
            addInterceptor(get() as HostApiInterceptor)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            clearText()
            if (isShowLogHTTP) {
                addInterceptor(get() as LoggingInterceptor)
                addNetworkInterceptor(StethoInterceptor())
            }
            build()
        }
    }

    factory(named(OKHttpUpload)) {
        with(OkHttpClient.Builder()) {
            addInterceptor { chain ->
                try {
                    val headers = chain.request().newBuilder()
                    headers.addHeader("Accept", "application/json")
                    headers.addHeader("Content-Type", "application/json")
                    (get() as DataStoreUtils).readStringData("TOKEN").let { token ->
                        if (token.isNotNull) {
                            headers.addHeader("TC-Token", token)
                        }
                    }
                    return@addInterceptor chain.proceed(headers.build())
                } catch (e: UnknownHostException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return@addInterceptor chain.proceed(chain.request())
            }
            addInterceptor(get() as HostApiInterceptor)
            connectTimeout(3, TimeUnit.MINUTES)
            readTimeout(3, TimeUnit.MINUTES)
            clearText()
            if (isShowLogHTTP) {
                addInterceptor(get() as LoggingInterceptor)
                addNetworkInterceptor(StethoInterceptor())
            }
            build()
        }
    }

    single(named(OKHttpApi)) {
        with(Retrofit.Builder()) {
            baseUrl(BASE_URL)
            client(get() as OkHttpClient)
            addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            build()
        }
    }

    single(named(OKHttpUpload)) {
        with(Retrofit.Builder()) {
            baseUrl(BASE_URL_UPLOAD)
            client(get() as OkHttpClient)
            addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            build()
        }
    }

    single { get<Retrofit>(named(OKHttpApi)).create(ApiService::class.java) }
}
