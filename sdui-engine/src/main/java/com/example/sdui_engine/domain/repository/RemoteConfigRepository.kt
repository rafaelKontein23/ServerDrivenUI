package com.example.sdui_engine.domain.repository

interface RemoteConfigRepository {
    suspend fun fetchConfig(): Boolean
    fun getJson(key: String): String
}
