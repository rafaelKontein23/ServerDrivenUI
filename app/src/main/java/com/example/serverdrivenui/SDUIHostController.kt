package com.example.serverdrivenui

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess


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
                    _isSuccess.value = true
                }
            }
        }
    }
    fun <T : View> findViewBySduiId(
        container: ViewGroup,
        id: String
    ): T? {
        return container.findViewWithTag(id)
    }
}
