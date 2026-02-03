package com.example.sdui_engine.data.model

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