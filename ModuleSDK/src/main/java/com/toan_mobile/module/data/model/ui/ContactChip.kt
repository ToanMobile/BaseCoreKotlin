package com.toan_mobile.module.data.model.ui

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.pchmn.materialchips.model.ChipInterface

class ContactChip : ChipInterface {
    private var id: Any? = null
    private var avatarUri: Uri? = null
    private var avatarDrawable: Drawable? = null
    private var label: String? = null
    private var info: String

    constructor(@NonNull id: Any?, @Nullable avatarUri: Uri?, @NonNull label: String, @Nullable info: String) {
        this.id = id
        this.avatarUri = avatarUri
        this.label = label
        this.info = info
    }

    constructor(@NonNull id: Any?, @Nullable avatarDrawable: Drawable?, @NonNull label: String, @Nullable info: String) {
        this.id = id
        this.avatarDrawable = avatarDrawable
        this.label = label
        this.info = info
    }

    constructor(@Nullable avatarUri: Uri?, @NonNull label: String, @Nullable info: String) {
        this.avatarUri = avatarUri
        this.label = label
        this.info = info
    }

    constructor(@Nullable avatarDrawable: Drawable?, @NonNull label: String, @Nullable info: String) {
        this.avatarDrawable = avatarDrawable
        this.label = label
        this.info = info
    }

    constructor(@NonNull id: Any?, @NonNull label: String, @Nullable info: String) {
        this.id = id
        this.label = label
        this.info = info
    }

    constructor(@NonNull label: String, @Nullable info: String) {
        this.label = label
        this.info = info
    }

    override fun getId(): Any? {
        return id
    }

    override fun getAvatarUri(): Uri? {
        return avatarUri
    }

    override fun getAvatarDrawable(): Drawable? {
        return avatarDrawable
    }

    override fun getLabel(): String {
        return label ?: ""
    }

    override fun getInfo(): String {
        return info
    }
}