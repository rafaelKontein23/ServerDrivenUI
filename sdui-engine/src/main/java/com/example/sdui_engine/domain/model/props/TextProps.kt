package com.example.sdui_engine.domain.model.props

data class TextProps(
    val id: String,
    val description: String,
    val accessibilityText: String,
    val marginTop: Int,
    val marginBottom: Int,
    val marginLeft: Int,
    val marginRight: Int,
    val gravity: String,
    val textColor: String,
    val textSize: Float,
    val textStyle: String
)
