package com.example.sdui_engine.data.remoteconfig

import com.example.sdui_engine.domain.repository.RemoteConfigRepository

class RemoteConfigRepositoryImpl(
    private val dataSource: RemoteConfigDataSource
) : RemoteConfigRepository {

    override suspend fun fetchConfig(): Boolean =
        dataSource.fetchAndActivate()

    override fun getJson(key: String): String =
        dataSource.getString(key)
}
