package com.example.sdui_engine.domain.model

import com.example.sdui_engine.data.model.RemoteConfigKeys

enum class SduiScreen(
    val remoteConfigKey: String
) {
    LOGIN(RemoteConfigKeys.DS_LOGIN_LAYOUT),
    REGISTER(RemoteConfigKeys.DS_REGISTER_LAYOUT),
    FORGOT_PASSWORD(RemoteConfigKeys.DS_FORGOT_PASSWORD_LAYOUT),

    HOME(RemoteConfigKeys.DS_HOME_LAYOUT),
    MESSAGE(RemoteConfigKeys.DS_MESSAGE_LAYOUT)
}