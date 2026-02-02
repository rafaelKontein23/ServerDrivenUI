package com.example.sdui_engine.domain.usecase

import com.example.sdui_engine.domain.result.LayoutResult
import com.example.sdui_engine.domain.remoteconfig.RemoteConfigKeys
import com.example.sdui_engine.domain.repository.RemoteConfigRepository
import javax.inject.Inject

class GetLoginLayoutUseCase @Inject constructor(
    private val repository: RemoteConfigRepository
) {

    suspend operator fun invoke(): LayoutResult {
        repository.fetchConfig()
        val json = repository.getJson(RemoteConfigKeys.DS_LOGIN_LAYOUT)

        return if (json.isNotBlank()) {
            LayoutResult.Success(json)
        } else {
            LayoutResult.Empty
        }
    }
}
