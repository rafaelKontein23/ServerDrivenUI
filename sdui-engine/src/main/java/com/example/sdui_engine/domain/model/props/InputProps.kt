package com.example.sdui_engine.domain.model.props

data class InputProps(
    val id: String,
    val hint: String,
    val inputType: String,
    val accessibilityText: String,
    val marginTop: Int = 0,
    val marginBottom: Int = 0,
    val marginLeft: Int = 0,
    val marginRight: Int = 0
)
