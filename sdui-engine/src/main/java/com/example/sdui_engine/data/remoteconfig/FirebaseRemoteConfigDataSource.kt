package com.example.sdui_engine.data.remoteconfig

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.tasks.await

class FirebaseRemoteConfigDataSource(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigDataSource {

    override suspend fun fetchAndActivate(): Boolean =
        remoteConfig.fetchAndActivate().await()

    override fun getString(key: String): String =
        remoteConfig.getString(key)
}
