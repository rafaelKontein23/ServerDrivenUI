package com.example.sdui_engine.di

import com.example.sdui_engine.data.parser.SduiParser
import com.example.sdui_engine.data.remoteconfig.FirebaseRemoteConfigDataSource
import com.example.sdui_engine.data.remoteconfig.RemoteConfigDataSource
import com.example.sdui_engine.data.remoteconfig.RemoteConfigRepositoryImpl
import com.example.sdui_engine.presentation.renderer.SduiRendererImpl
import com.example.sdui_engine.data.model.RemoteConfigKeys
import com.example.sdui_engine.domain.repository.RemoteConfigRepository
import com.example.sdui_engine.presentation.renderer.SduiRenderer
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {

    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = FirebaseRemoteConfig.getInstance()

        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0) // debug
            .build()

        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(
            mapOf(
                RemoteConfigKeys.DS_LOGIN_LAYOUT to "{}"
            )
        )

        return remoteConfig
    }

    @Provides
    @Singleton
    fun provideRemoteConfigDataSource(
        remoteConfig: FirebaseRemoteConfig
    ): RemoteConfigDataSource {
        return FirebaseRemoteConfigDataSource(remoteConfig)
    }

    @Provides
    @Singleton
    fun provideRemoteConfigRepository(dataSource: RemoteConfigDataSource): RemoteConfigRepository =
        RemoteConfigRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideSduiParser(gson: Gson): SduiParser = SduiParser(gson)

    @Provides
    @Singleton
    fun provideSduiRenderer(): SduiRenderer = SduiRendererImpl()
}