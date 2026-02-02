package com.example.sdui_engine.data.mapper

import com.example.sdui_engine.domain.model.props.ButtonProps
import com.example.sdui_engine.domain.model.props.CompoundTextProps
import com.example.sdui_engine.domain.model.props.InputProps
import com.example.sdui_engine.domain.model.props.TextProps

object PropsMapper {

    fun mapText(props: Map<String, String>) = TextProps(
        id = props["id"].orEmpty(),
        description = props["description"].orEmpty(),
        accessibilityText = props["accessibility_text"].orEmpty(),
        marginTop = props["margin_top"]?.toInt() ?: 0,
        marginBottom = props["margin_bottom"]?.toInt() ?: 0,
        marginLeft = props["margin_left"]?.toInt() ?: 0,
        marginRight = props["margin_right"]?.toInt() ?: 0,
        gravity = props["gravity"].orEmpty(),
        textColor = props["text_color"].orEmpty(),
        textSize = props["text_size"]?.toFloat() ?: 14f,
        textStyle = props["text_style"].orEmpty()
    )

    fun mapInput(props: Map<String, String>) = InputProps(
        id = props["id"].orEmpty(),
        hint = props["hint"].orEmpty(),
        inputType = props["inputType"].orEmpty(),
        accessibilityText = props["accessibility_text"].orEmpty()
    )

    fun mapButton(props: Map<String, String>) = ButtonProps(
        id = props["id"].orEmpty(),
        text = props["text"].orEmpty(),
        accessibilityText = props["accessibility_text"].orEmpty(),
        backgroundColor = props["background_color"].orEmpty(),
        marginTop = props["margin_top"]?.toInt() ?: 0,
        marginBottom = props["margin_bottom"]?.toInt() ?: 0,
        marginLeft = props["margin_left"]?.toInt() ?: 0,
        marginRight = props["margin_right"]?.toInt() ?: 0
    )


    fun mapCompoundText(props: Map<String, String>) = CompoundTextProps(
        id = props["id"].orEmpty(),
        description = props["description"].orEmpty(),
        descriptionBold = props["description_bold"].orEmpty(),
        accessibilityText = props["accessibility_text"].orEmpty(),
        gravity = props["gravity"].orEmpty(),
        textColor = props["text_color"].orEmpty(),
        colorBold = props["color_bold"].orEmpty(),
        textSize = props["text_size"]?.toFloat() ?: 14f,
        textStyle = props["text_style"].orEmpty(),
        textStyleBold = props["text_style_bold"].orEmpty(),
        marginTop = props["margin_top"]?.toInt() ?: 0,
        marginBottom = props["margin_bottom"]?.toInt() ?: 0,
        marginLeft = props["margin_left"]?.toInt() ?: 0,
        marginRight = props["margin_right"]?.toInt() ?: 0
    )

}
