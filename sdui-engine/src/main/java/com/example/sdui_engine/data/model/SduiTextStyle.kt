package com.example.sdui_engine.data.model

import android.graphics.Typeface

enum class SduiTextStyle(val nativeStyle: Int) {
    REGULAR(Typeface.NORMAL),
    BOLD(Typeface.BOLD),
    ITALIC(Typeface.ITALIC),
    BOLD_ITALIC(Typeface.BOLD_ITALIC);

    companion object {
        fun fromString(value: String?): SduiTextStyle {
            return when (value?.lowercase()) {
                "bold" -> BOLD
                "italic" -> ITALIC
                "bold_italic" -> BOLD_ITALIC
                else -> REGULAR
            }
        }
    }
}