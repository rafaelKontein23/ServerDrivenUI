package com.example.sdui_engine.domain.result

sealed class LayoutResult {
    data class Success(val json: String) : LayoutResult()
    data object Empty : LayoutResult()
}