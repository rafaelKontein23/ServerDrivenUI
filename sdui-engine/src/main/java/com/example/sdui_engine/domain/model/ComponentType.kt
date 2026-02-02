package com.example.sdui_engine.domain.model

enum class ComponentType {
    TEXT,
    INPUT,
    BUTTON,
    COMPOUND_TEXT;

    companion object {
        fun from(value: String): ComponentType? =
            entries.firstOrNull { it.name == value.uppercase() }
    }
}