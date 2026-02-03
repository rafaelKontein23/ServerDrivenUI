package com.example.sdui_engine.data.model.props

import com.google.gson.annotations.SerializedName

data class ButtonProps(
    val id: String,
    val text: String,
    val accessibilityText: String,
    val backgroundColor: String,

    @SerializedName("margin_top")
    val marginTop: Int = 0,

    @SerializedName("margin_bottom")
    val marginBottom: Int = 0,

    @SerializedName("margin_left")
    val marginLeft: Int = 0,

    @SerializedName("margin_right")
    val marginRight: Int = 0
)