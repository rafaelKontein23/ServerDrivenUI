package com.example.sdui_engine.presentation.renderer

import android.view.View
import android.view.ViewGroup
import com.example.sdui_engine.data.model.SduiComponent

interface SduiRenderer {
    suspend fun render(
        components: List<SduiComponent>,
        container: ViewGroup,
        onAction: (String) -> Unit
    )
    fun findView(id: String): View?

    fun getAllInputData(): Map<String, String>

    fun clearAllErrors()

    fun setThemeOverrides(colors: Map<String, String>)
}