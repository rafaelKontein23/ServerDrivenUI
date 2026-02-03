package com.example.sdui_engine.presentation.state

import com.example.sdui_engine.data.model.SduiComponent

sealed class SDUIViewState {
    object Loading : SDUIViewState()
    data class Content(
        val components: List<SduiComponent>
    ) : SDUIViewState()

    data class Error(
        val message: String? = null
    ) : SDUIViewState()
}