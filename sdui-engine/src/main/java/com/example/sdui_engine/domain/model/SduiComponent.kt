package com.example.sdui_engine.domain.model

import com.example.sdui_engine.domain.model.props.ButtonProps
import com.example.sdui_engine.domain.model.props.CompoundTextProps
import com.example.sdui_engine.domain.model.props.InputProps
import com.example.sdui_engine.domain.model.props.TextProps

sealed class SduiComponent {
    data class Text(val props: TextProps) : SduiComponent()
    data class Input(val props: InputProps) : SduiComponent()
    data class Button(val props: ButtonProps) : SduiComponent()
    data class CompoundText(val props: CompoundTextProps) : SduiComponent()
}