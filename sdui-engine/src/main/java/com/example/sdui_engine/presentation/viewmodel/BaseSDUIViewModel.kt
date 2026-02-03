package com.example.sdui_engine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sdui_engine.data.parser.SduiParser
import com.example.sdui_engine.domain.result.LayoutResult
import com.example.sdui_engine.domain.result.ParseResult
import com.example.sdui_engine.presentation.state.SDUIViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseSDUIViewModel(
    private val parser: SduiParser
) : ViewModel() {

    protected val _uiState =
        MutableStateFlow<SDUIViewState>(SDUIViewState.Loading)

    val uiState: StateFlow<SDUIViewState> = _uiState

    protected fun load(
        loadLayout: suspend () -> LayoutResult
    ) {
        viewModelScope.launch {

            _uiState.value = SDUIViewState.Loading

            when (val layoutResult = loadLayout()) {

                is LayoutResult.Success -> {
                    when (val parseResult =
                        parser.parse(layoutResult.json)) {

                        is ParseResult.Success -> {
                            _uiState.value =
                                SDUIViewState.Content(parseResult.components)
                        }

                        is ParseResult.Error -> {
                            _uiState.value =
                                SDUIViewState.Error(parseResult.reason)
                        }
                    }
                }

                is LayoutResult.Empty -> {
                    _uiState.value =
                        SDUIViewState.Error("Layout vazio")
                }
            }
        }
    }
}