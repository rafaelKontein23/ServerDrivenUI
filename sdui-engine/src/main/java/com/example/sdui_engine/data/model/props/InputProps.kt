package com.example.sdui_engine.data.model.props

import com.google.gson.annotations.SerializedName
data class InputProps(
    val id: String,
    val hint: String,
    val inputKeyboardType: String,
    val accessibilityText: String,

    @SerializedName("margin_top")
    val marginTop: Int = 0,

    @SerializedName("margin_bottom")
    val marginBottom: Int = 0,

    @SerializedName("margin_left")
    val marginLeft: Int = 0,

    @SerializedName("margin_right")
    val marginRight: Int = 0
)