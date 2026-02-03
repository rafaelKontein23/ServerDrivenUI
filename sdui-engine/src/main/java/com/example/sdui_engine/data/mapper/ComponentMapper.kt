package com.example.sdui_engine.data.mapper

import com.example.sdui_engine.data.dto.ComponentDto
import com.example.sdui_engine.data.model.ComponentType
import com.example.sdui_engine.data.model.SduiComponent

object ComponentMapper {

    fun map(dto: ComponentDto): SduiComponent? {
        val type = ComponentType.from(dto.component) ?: return null

        return when (type) {
            ComponentType.TEXT ->
                SduiComponent.Text(PropsMapper.mapText(dto.props))

            ComponentType.INPUT ->
                SduiComponent.Input(PropsMapper.mapInput(dto.props))

            ComponentType.BUTTON ->
                SduiComponent.Button(PropsMapper.mapButton(dto.props))

            ComponentType.COMPOUND_TEXT ->
                SduiComponent.CompoundText(PropsMapper.mapCompoundText(dto.props))
        }
    }
}
