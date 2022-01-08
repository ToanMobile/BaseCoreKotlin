package com.toan_mobile.module.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.toan_mobile.module.R

fun Context.sendSms(phones: List<String?>) {
    if (phones.isEmpty()) return
    var smsto = "smsto:$phones"
    if (phones.size > 1)
        for (i in 1 until phones.size) {
            if (phones[i]?.isNotEmpty() == true)
                smsto += ";${phones[i]}"
        }
    val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(smsto))
    smsIntent.putExtra("sms_body", getString(R.string.content_invite))
    startActivity(smsIntent)
}

fun Context.share(subject: String?, shareMessage: String, title: String? = "") {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        if (subject != null)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, title))
    } catch (e: Exception) {
        print(e)
    }
}
