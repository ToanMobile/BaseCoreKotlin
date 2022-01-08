package com.toan_mobile.module.data.intercepter

import com.netacom.base.chat.util.isNotNull
import com.toan_mobile.module.data.local.prefs.DataStoreUtils
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

/**Created by vantoan on 28/Oct/2020
Company: Netacom.
Email: hvtoan.dev@gmail.com
 **/

class HostApiInterceptor(
    private val dataStore: DataStoreUtils
) : Interceptor {
    @Volatile private var host: HttpUrl? = null

    fun setHost(url: String) {
        this.host = url.toHttpUrlOrNull()
    }

    fun switch(request: Request) {
        val currentUrl = "${request.url.scheme}://${request.url.host}/"
        if (currentUrl != host.toString()) return
        // setHost(BuildConfig.API_URL)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var newRequest = "EndPoint.URL_API".toHttpUrlOrNull()?.let {
            val newUrl = chain.request().url.newBuilder()
                .host(it.toUrl().toURI().host)
                .port(it.port)
                .build()
            val headers = chain.request().headers.newBuilder()
            headers.add("Accept", "application/json")
            headers.add("Content-Type", "application/json")
            dataStore.readStringData("").let { token ->
                if (token.isNotNull) {
                    headers.add("TC-Token", token)
                }
            }
            return@let chain.request().newBuilder()
                .headers(headers.build())
                .url(newUrl)
                .build()
        }
        if (newRequest == null) newRequest = chain.request()
        return chain.proceed(newRequest)
    }
}
