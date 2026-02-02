package com.example.sdui_engine.sdui

import android.view.ViewGroup
import com.example.sdui_engine.domain.model.SduiComponent

interface SduiRenderer {
    fun render(
        components: List<SduiComponent>,
        container: ViewGroup
    )
}
