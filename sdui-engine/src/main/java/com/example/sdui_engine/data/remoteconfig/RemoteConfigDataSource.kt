package com.example.sdui_engine.data.remoteconfig

interface RemoteConfigDataSource {
    suspend fun fetchAndActivate(): Boolean
    fun getString(key: String): String
}
