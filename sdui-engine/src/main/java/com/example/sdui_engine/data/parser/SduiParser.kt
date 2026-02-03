package com.example.sdui_engine.data.parser

import com.example.sdui_engine.data.dto.ScreenDto
import com.example.sdui_engine.data.mapper.ComponentMapper
import com.example.sdui_engine.domain.result.ParseResult
import com.google.gson.Gson

class SduiParser(
    private val gson: Gson
) {

    fun parse(json: String): ParseResult {
        if (json.isBlank() || json == "{}") {
            return ParseResult.Error("JSON vazio ou inválido")
        }

        return try {
            val screenDto = gson.fromJson(json, ScreenDto::class.java)

            if (screenDto.components.isNullOrEmpty()) {
                ParseResult.Error("JSON não contém components")
            } else {
                val components = screenDto.components.mapNotNull {
                    ComponentMapper.map(it)
                }

                if (components.isEmpty()) {
                    ParseResult.Error("Nenhum componente válido após o parse")
                } else {
                    ParseResult.Success(components)
                }
            }

        } catch (e: Exception) {
            ParseResult.Error("Erro ao parsear JSON: ${e.message}")
        }
    }
}