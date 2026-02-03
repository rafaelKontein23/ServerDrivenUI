package com.example.sdui_engine.data.model.props

import com.google.gson.annotations.SerializedName

data class TextProps(

    val id: String,

    val description: String,

    @SerializedName("accessibility_text")
    val accessibilityText: String? = null,

    @SerializedName("margin_top")
    val marginTop: Int = 0,

    @SerializedName("margin_bottom")
    val marginBottom: Int = 0,

    @SerializedName("margin_left")
    val marginLeft: Int = 0,

    @SerializedName("margin_right")
    val marginRight: Int = 0,

    val gravity: String = "START",

    @SerializedName("text_color")
    val textColor: String = "#000000",

    @SerializedName("text_size")
    val textSize: Float = 14f,

    @SerializedName("text_style")
    val textStyle: String = "REGULAR"
)