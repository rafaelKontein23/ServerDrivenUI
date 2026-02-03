package com.example.serverdrivenui

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sdui_engine.presentation.host.SDUIHostView
import com.example.sdui_engine.presentation.state.SDUIViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SDUIHostController @Inject constructor(
    private val hostView: SDUIHostView
) {

    fun bind(
        lifecycleOwner: LifecycleOwner,
        stateFlow: StateFlow<SDUIViewState>,
        container: ViewGroup,
        onAction: (String) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                stateFlow.collect {
                    hostView.render(it, container, onAction)
                }
            }
        }
    }
}
