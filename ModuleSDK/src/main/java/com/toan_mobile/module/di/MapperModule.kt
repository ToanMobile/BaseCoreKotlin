package com.toan_mobile.module.di

import com.toan_mobile.module.mapper.Contact_DB_UI_Mapper
import com.toan_mobile.module.mapper.DB_UI_Mapper
import com.toan_mobile.module.mapper.LocalContact_DB_UI_Mapper
import org.koin.dsl.module

val mapperModule = module {
    single { Contact_DB_UI_Mapper(get()) }
    single { LocalContact_DB_UI_Mapper() }

    //Core Use
    single { DB_UI_Mapper(get(), get()) }
}
