package com.example.serverdrivenui

import com.example.sdui_engine.data.parser.SduiParser
import com.example.sdui_engine.domain.model.SduiScreen
import com.example.sdui_engine.domain.usecase.GetSduiLayoutUseCase
import com.example.sdui_engine.presentation.viewmodel.BaseSDUIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSduiLayoutUseCase: GetSduiLayoutUseCase,
    parser: SduiParser
) : BaseSDUIViewModel(parser) {

    fun loadScreen() {
        load { getSduiLayoutUseCase(SduiScreen.LOGIN) }
    }
}