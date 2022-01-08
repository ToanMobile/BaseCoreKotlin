package com.toan_mobile.module.di

import com.toan_mobile.module.core.config.ThemeHelperImpl
import com.toan_mobile.module.util.dispatchers.CoroutineDispatchers
import com.toan_mobile.module.util.dispatchers.DefaultCoroutineDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    factory<CoroutineDispatchers> { DefaultCoroutineDispatchers() }
    factory { ThemeHelperImpl(get(), androidContext()) }
}

val workerServiceModule = module {
    // single<SimpleWorkerService>()
}

val workerScopedModule = module {
    // worker { SimpleWorker(get(), androidContext(), it.get()) }
}

val allModules = listOf(appModule, workerServiceModule, workerScopedModule, databaseModule, networkModule, repositoryModule, grpcModule, mapperModule, viewModelModule)
