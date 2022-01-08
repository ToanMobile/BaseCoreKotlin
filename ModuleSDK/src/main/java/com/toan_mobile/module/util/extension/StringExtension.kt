package com.toan_mobile.module.util.extension

import com.netacom.base.chat.util.unAccentText
import java.util.*

fun String.deAccentText(): String =
    this.unAccentText().lowercase(Locale.getDefault())
