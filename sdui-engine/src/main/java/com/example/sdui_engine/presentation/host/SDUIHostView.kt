package com.example.sdui_engine.presentation.host

import android.view.ViewGroup
import com.example.sdui_engine.presentation.renderer.SduiRenderer
import com.example.sdui_engine.presentation.state.SDUIViewState
import javax.inject.Inject

class SDUIHostView @Inject constructor(
    private val renderer: SduiRenderer
) {

    fun setTheme(colorOverrides: Map<String, String>) {
        renderer.setThemeOverrides(colorOverrides)
    }

    suspend fun render(
        state: SDUIViewState,
        container: ViewGroup,
        onAction: (String) -> Unit
    ) {
        when (state) {

            SDUIViewState.Loading -> {
                container.removeAllViews()
            }

            is SDUIViewState.Content -> {
                container.removeAllViews()
                renderer.render(
                    components = state.components,
                    container = container,
                    onAction = onAction
                )
            }

            is SDUIViewState.Error -> {
                container.removeAllViews()
            }
        }
    }
}