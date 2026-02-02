package com.example.sdui_engine.data.parser

import com.example.sdui_engine.data.dto.ScreenDto
import com.example.sdui_engine.data.mapper.ComponentMapper
import com.example.sdui_engine.domain.model.SduiComponent
import com.google.gson.Gson

class SduiParser(
    private val gson: Gson
) {

    fun parse(json: String): List<SduiComponent> {
        val screenDto = gson.fromJson(json, ScreenDto::class.java)

        return screenDto?.components?.mapNotNull {
            ComponentMapper.map(it)
        } ?: emptyList()
    }
}
