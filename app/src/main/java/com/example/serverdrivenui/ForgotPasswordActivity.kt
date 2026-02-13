package com.example.serverdrivenui

import android.util.Log
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {

    @Inject
    lateinit var sduiHostController: SDUIHostController
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val container = findViewById<LinearLayout>(R.id.sdui_container)

        sduiHostController.bind(
            lifecycleOwner = this,
            stateFlow = viewModel.uiState,
            container = container,
            onAction = { actionId ->
                handleSduiActions(actionId)
            }
        )

        viewModel.loadScreen()
    }

    private fun handleSduiActions(actionId: String) {
        when (actionId) {
            "btn_back" -> {
                finish()
            }
            "btn_send_recovery" -> {
                Toast.makeText(this, "Link enviado com sucesso!", Toast.LENGTH_LONG).show()
            }
            else -> {
                Log.d("SDUI_DEBUG", "Ação não mapeada: $actionId")
            }
        }
    }
}