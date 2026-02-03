package com.example.sdui_engine.domain.result

import com.example.sdui_engine.data.model.SduiComponent

sealed class ParseResult {
    data class Success(val components: List<SduiComponent>) : ParseResult()
    data class Error(val reason: String) : ParseResult()
}