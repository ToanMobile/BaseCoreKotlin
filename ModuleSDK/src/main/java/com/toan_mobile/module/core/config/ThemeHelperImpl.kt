package com.toan_mobile.module.core.config

import android.content.Context
import com.toan_mobile.module.data.local.prefs.DataStoreUtils

class ThemeHelperImpl constructor(
    private val dataStoreUtils: DataStoreUtils,
    private val context: Context
) {

    /*var neTheme: NeTheme?
        get() = preferencesHelperImpl.neTheme
        set(value) {
            preferencesHelperImpl.neTheme = value
        }

    var displayMode: Int
        get() = preferencesHelperImpl.displayMode
        set(value) {
            preferencesHelperImpl.displayMode = value
        }

    val mainColor: Int
        get() = try {
            preferencesHelperImpl.neTheme?.mainColor?.asColor ?: ColorDefine.BLUE_MAIN_COLOR.asColor!!
        } catch (e: Exception) {
            ColorDefine.BLUE_MAIN_COLOR.asColor!!
        }

    val subColor: Int
        get() = if (isDarkMode) {
            try {
                preferencesHelperImpl.neTheme?.subColorDark?.asColor ?: ColorDefine.ORANGE_SUB_COLOR_DARK.asColor!!
            } catch (e: Exception) {
                ColorDefine.ORANGE_SUB_COLOR_DARK.asColor!!
            }
        } else {
            try {
                preferencesHelperImpl.neTheme?.subColorLight?.asColor ?: ColorDefine.ORANGE_SUB_COLOR_LIGHT.asColor!!
            } catch (e: Exception) {
                ColorDefine.ORANGE_SUB_COLOR_LIGHT.asColor!!
            }
        }

    val toolbarDrawableId: Int
        get() = try {
            if ((preferencesHelperImpl.neTheme?.toolbarDrawable)?.startsWith("#") == true) {
                (preferencesHelperImpl.neTheme?.toolbarDrawable)?.asColor ?: R.drawable.bg_header_orange
            } else {
                preferencesHelperImpl.neTheme?.toolbarDrawable?.let { resourceName ->
                    val resourceId = application.resIdByNameDrawable(resourceName)
                    if (resourceId.isNotNull && resourceId!! > 0 && application.resNameById(resourceId) == resourceName) {
                        resourceId
                    } else {
                        R.drawable.bg_header_orange
                    }
                } ?: R.drawable.bg_header_orange
            }
        } catch (e: Exception) {
            R.drawable.bg_header_orange
        }

    val isDarkMode: Boolean
        get() = when (displayMode) {
            Constants.DARK_MODE -> {
                true
            }
            Constants.SYSTEM_MODE -> {
                ThemeUtils.isOsDarkTheme(application)
            }
            else -> {
                false
            }
        }

    private val secretColor: Int
        get() = if (isDarkMode) {
            R.color.color_grey_a0a0a0
        } else {
            R.color.color_black_25272a
        }

    private val secretMessageColor: Int get() = R.color.color_black_4b4c4e

    fun setThemeColorForViews(vararg views: View?) {
        views.forEach {
            when (it) {
                is AppCompatTextView -> it.setTextColorTheme(mainColor)
                is AppCompatEditText -> it.setTextColorTheme(mainColor)
                is ImageView -> it.setImageColorTheme(mainColor)
                is CoreToolBar -> it.setBackgroundTheme(
                    isDarkMode,
                    toolbarDrawableId
                )
                is BottomNavigationView -> {
                    it.setupNavigationTheme(createThemeMenu())
                }
                is AppCompatButton -> {
                    it.setButtonTheme(mainColor)
                }
                is TabLayout -> {
                    it.setTabLayoutTheme(mainColor)
                }
                is ProgressBar -> {
                    it.setProgressTintListTheme(mainColor)
                }
                is SwitchCompat -> {
                    it.setSwitchColor(mainColor)
                }
                is AppCompatCheckBox -> {
                    val colorList = ColorStateList(
                        arrayOf(
                            intArrayOf(-android.R.attr.state_checked),  // uncheck
                            intArrayOf(android.R.attr.state_checked)    // checked
                        ),
                        intArrayOf(
                            Color.GRAY,     // The color for the uncheck state
                            mainColor        // The color for the checked state
                        )
                    )
                    it.buttonTintList = colorList
                }
                else -> {
                    it?.setBackgroundColorTheme(mainColor)
                }
            }
        }
    }

    fun setThemeColorForSecretChat(vararg views: View) {
        try {
            views.forEach {
                when (it) {
                    is AppCompatTextView -> it.setTextColorTheme(secretColor)
                    is AppCompatEditText -> it.setTextColorTheme(secretColor)
                    is ImageView -> it.setImageColorTheme(secretColor)
                    is AppCompatButton -> {
                        it.setButtonTheme(secretColor)
                    }
                    is TabLayout -> {
                        it.setTabLayoutTheme(secretColor)
                    }
                    is ProgressBar -> {
                        it.setProgressTintListTheme(secretColor)
                    }
                    else -> {
                        it.setBackgroundColorTheme(secretColor)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setHeaderTheme(view: View) {
        if (isDarkMode) {
            view.setBackgroundColor(
                ContextCompat.getColor(
                    application,
                    R.color.color_black_25272a
                )
            )
        } else {
            when {
                isResource(application, toolbarDrawableId) -> {
                    view.setBackgroundResource(toolbarDrawableId)
                }
                else -> {
                    view.setBackgroundColor(toolbarDrawableId)
                }
            }
        }
    }

    private fun setThemeColorForViews(views: List<View>) {
        views.forEach {
            when (it) {
                is TextView -> it.setTextColorTheme(mainColor)
                is ImageView -> it.setImageColorTheme(mainColor)
            }
        }
    }

    fun setThemeStateListForButtonAction(view: View) {
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(android.R.attr.state_enabled),
            createShapeDrawable(mainColor)
        ) // if activated true
        if (displayMode == Constants.DARK_MODE) {
            // Logger.d("Liem - button for dark mode")
            stateListDrawable.addState(
                StateSet.WILD_CARD,
                createShapeDrawable(ContextCompat.getColor(application, R.color.color_grey_b7b7b7))
            )
        } else {
            // Logger.d("Liem - button for light mode")
            stateListDrawable.addState(
                StateSet.WILD_CARD,
                createShapeDrawable(
                    ContextCompat.getColor(application, R.color.color_disable_btn)
                )
            )
        } // rest all the state
        view.background = stateListDrawable
    }

    fun createThemeColorStateList(): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(
            intArrayOf(android.R.attr.state_enabled),
            createShapeDrawable(ContextCompat.getColor(application, mainColor))
        ) // if activated true
        stateListDrawable.addState(
            StateSet.WILD_CARD,
            createShapeDrawable(
                ContextCompat.getColor(application, R.color.color_grey_3325272A)
            )
        )
        return stateListDrawable
    }

    private fun createThemeMenu(): ColorStateList {
        return ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked), // Disabled
                intArrayOf(android.R.attr.state_checked) // Enabled
            ),
            intArrayOf(
                ContextCompat.getColor(Utils.getApp(), R.color.color_grey_b7b7b7), // The color for the Disabled state
                getThemeColor(mainColor) // The color for the Enabled state
            )
        )
    }

    private fun createShapeDrawable(color: Int): GradientDrawable {
        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.setColor(color)
        shape.cornerRadius = 4f
        return shape
    }

    fun setThemeColorForDrawable(drawables: Drawable?): Drawable? {
        drawables?.colorFilter = PorterDuffColorFilter(getThemeColor(mainColor), PorterDuff.Mode.SRC_IN)
        return drawables
    }

    fun setThemeColorForSubBackground(vararg views: View) {
        views.forEach {
            when (it) {
                is AppCompatImageView -> {
                    it.setBackgroundColorTheme(subColor)
                }
                else -> {
                    it.setBackgroundColorTheme(subColor)
                }
            }
        }
    }

    fun setThemeColorForBackground(view: View) {
        view.setBackgroundColorTheme(mainColor)
    }

    fun setThemeColorForSecretBackground(view: View) {
        view.setBackgroundColorTheme(secretColor)
    }

    fun setButtonBackgroundAndText(button: AppCompatButton) {
        button.setButtonTheme(mainColor)
        button.setTextColor(getThemeColor(mainColor))
    }

    fun setButtonTextColor(button: AppCompatButton) {
        button.setTextColor(getThemeColor(mainColor))
    }

    fun setThemeColorForMessage(view: View, messageType: Int, groupType: Int) {
        // only set background opacity for background , needn't change
        when (messageType) {
            ChatMessageType.MY_MESSAGE_TEXT, ChatMessageType.MY_MESSAGE_REPLY
            -> view.setBackgroundResource(R.drawable.bg_single_sender)
            ChatMessageType.MY_MESSAGE_VIDEO, ChatMessageType.MY_MESSAGE_AUDIO, ChatMessageType.MY_MESSAGE_CALL, ChatMessageType.MY_MESSAGE_FILE
            -> view.setBackgroundResource(R.drawable.bg_media_opacity_45)
            ChatMessageType.PARTNER_MESSAGE_VIDEO, ChatMessageType.PARTNER_MESSAGE_AUDIO, ChatMessageType.PARTNER_MESSAGE_CALL, ChatMessageType.PARTNER_MESSAGE_FILE
            -> view.setBackgroundResource(R.drawable.bg_media_opacity_30)
        }

        // set color for message chat
        if (groupType == GROUP_TYPE_PRIVATE) {
            view.setBackgroundColorTheme(secretMessageColor)
        } else {
            view.setBackgroundColorTheme(mainColor)
        }
    }

    fun setThemeColorForBorder(drawable: Drawable) {
        (drawable as? GradientDrawable)?.let {
            it.setStroke(2, mainColor)
        }
    }

    fun removeThemeColorForBorder(drawable: Drawable) {
        val strokeDrawable = drawable as? GradientDrawable
        strokeDrawable?.setStroke(2, ContextCompat.getColor(application, android.R.color.transparent))
    }

    fun resetToDefault() {
        preferencesHelperImpl.neTheme = null
        preferencesHelperImpl.displayMode = Constants.LIGHT_MODE
        ThemeUtils.enableDisplayMode(Constants.LIGHT_MODE)
    }

    fun setColorForEmptyView(imageView: ImageView) {
        if (imageView is LayerDrawable) {

            val layerList = imageView.drawable.mutate() as LayerDrawable

            val emptyBackground =
                layerList.findDrawableByLayerId(R.id.ic_empty_background).mutate()

            DrawableCompat.setTint(
                emptyBackground,
                getThemeColor(subColor)
            )

            val contain = layerList.findDrawableByLayerId(R.id.ic_contain).mutate()

            DrawableCompat.setTint(
                contain,
                getThemeColor(mainColor)
            )
        }
    }

    fun setThemeColorForButton(button: AppCompatButton) {
        val bgColor = try {
            preferencesHelperImpl.neTheme?.subColorLight?.asColor ?: ColorDefine.ORANGE_SUB_COLOR_LIGHT.asColor!!
        } catch (e: Exception) {
            ColorDefine.ORANGE_SUB_COLOR_LIGHT.asColor!!
        }
        button.setButtonTheme(bgColor)
        button.setTextColor(getThemeColor(mainColor))
    }*/
}
