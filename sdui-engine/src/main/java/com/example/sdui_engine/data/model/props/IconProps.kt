package com.example.sdui_engine.data.model.props

import com.google.gson.annotations.SerializedName

data class IconProps(
    val id: String,

    @SerializedName("icon_res")
    val iconRes: String,

    @SerializedName("icon_color")
    val iconColor: String,

    val size: Int = 24,

    val action: String = "",

    @SerializedName("accessibility_text")
    val accessibilityText: String,

    @SerializedName("margin_top")
    val marginTop: Int = 0,

    @SerializedName("margin_bottom")
    val marginBottom: Int = 0,

    @SerializedName("margin_left")
    val marginLeft: Int = 0,

    @SerializedName("margin_right")
    val marginRight: Int = 0,

    val gravity: String = "START"
)