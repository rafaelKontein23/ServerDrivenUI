package com.example.sdui_engine.domain.usecase

import com.example.sdui_engine.domain.model.SduiScreen
import com.example.sdui_engine.domain.repository.RemoteConfigRepository
import com.example.sdui_engine.domain.result.LayoutResult
import javax.inject.Inject

class GetSduiLayoutUseCase @Inject constructor(
    private val repository: RemoteConfigRepository
) {

    suspend operator fun invoke(screen: SduiScreen): LayoutResult {
        repository.fetchConfig()

        val json = repository.getJson(screen.remoteConfigKey)

        return when {
            json.isBlank() -> LayoutResult.Empty
            else -> LayoutResult.Success(json)
        }
    }
}