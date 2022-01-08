package com.toan_mobile.module.di

import com.toan_mobile.module.data.repository.ContactRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ContactRepository(get(), get(), get(), get()) }
}
