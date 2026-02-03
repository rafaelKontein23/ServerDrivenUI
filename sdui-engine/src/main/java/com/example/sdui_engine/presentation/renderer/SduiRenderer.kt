package com.example.sdui_engine.presentation.renderer

import android.view.ViewGroup
import com.example.sdui_engine.data.model.SduiComponent

interface SduiRenderer {
    fun render(
        components: List<SduiComponent>,
        container: ViewGroup,
        onAction: (String) -> Unit
    )
}