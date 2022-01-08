/*
 * *Created by NetaloTeamAndroid on 2020
 * Company: Netacom.
 *  *
 */

package com.toan_mobile.module.core

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.netacom.base.chat.BuildConfig
import com.netacom.base.chat.app.isShowLog
import com.netacom.base.chat.logger.AndroidLogAdapter
import com.netacom.base.chat.logger.FormatStrategy
import com.netacom.base.chat.logger.Logger
import com.netacom.base.chat.logger.PrettyFormatStrategy
import com.toan_mobile.module.di.allModules
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class BaseSDKApplication : MultiDexApplication() {
    private val REALM_DB_NAME = "SDKNivalabs.realm"
    private val SCHEMA_VERSION = 1L

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initRealm()
        initKoin()
        setUpVectorResourceForAndroidKitkat()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(androidContext = this@BaseSDKApplication)
            androidFileProperties()
            fragmentFactory()
            workManagerFactory()
            modules(allModules)
        }
    }

    private fun initLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("MySDK")
            .build()
        Logger.addLogAdapter(
            object : AndroidLogAdapter(formatStrategy) {
                override fun isLoggable(priority: Int, tag: String?): Boolean {
                    return isShowLog
                }
            }
        )
    }

    private fun initRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name(REALM_DB_NAME)
            .schemaVersion(SCHEMA_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ERROR)
        }
    }

    private fun setUpVectorResourceForAndroidKitkat() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}
