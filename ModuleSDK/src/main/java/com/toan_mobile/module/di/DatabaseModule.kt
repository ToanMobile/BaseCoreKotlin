package com.toan_mobile.module.di

import com.toan_mobile.module.data.local.db.DbManager
import com.toan_mobile.module.data.local.prefs.DataStoreUtils
import com.toan_mobile.module.data.local.prefs.StoreUtil
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DataStoreUtils(androidContext()) }

    //Core Use
    single { StoreUtil(get()) }
    single { DbManager() }
}
