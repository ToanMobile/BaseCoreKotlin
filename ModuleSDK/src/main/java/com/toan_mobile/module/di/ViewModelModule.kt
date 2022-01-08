package com.toan_mobile.module.di

import com.toan_mobile.module.ui.call.CallViewModel
import com.toan_mobile.module.ui.call.ContactViewModel
import com.toan_mobile.module.ui.call.ProfileViewModel
import com.toan_mobile.module.ui.conversation.ConversationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // Call
    viewModel { CallViewModel(get()) }

    // Contact
    viewModel { ContactViewModel(get()) }

    // Conversation
    viewModel { ConversationViewModel(get()) }

    // Profile
    viewModel { ProfileViewModel(get()) }
    // viewModel { (posterId: Long) -> PosterDetailViewModel(posterId, get()) }
}
