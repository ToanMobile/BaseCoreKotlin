package com.toan_mobile.module.util.startup

import android.content.Context
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import okhttp3.OkHttpClient

class CoilInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        val imageLoader = ImageLoader.Builder(context)
            .okHttpClient {
                OkHttpClient.Builder()
                    // .cache(CoilUtils.createDefaultCache(context))
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // return listOf(TimberInitializer::class.java)
        return listOf()
    }
}
