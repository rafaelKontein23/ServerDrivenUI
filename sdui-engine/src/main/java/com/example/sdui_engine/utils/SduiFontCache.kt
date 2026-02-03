package com.example.sdui_engine.utils

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.example.sdui_engine.R

object SduiFontCache {
    private var poppinsMain: Typeface? = null

    fun getPoppins(context: Context): Typeface {
        if (poppinsMain == null) {
            poppinsMain = ResourcesCompat.getFont(context, R.font.poppins_family)
        }
        return poppinsMain!!
    }
}