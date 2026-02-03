package com.example.serverdrivenui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var sduiHostController: SDUIHostController

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        val container = findViewById<LinearLayout>(R.id.sdui_register_container)

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
            "btn_login" ->
                Toast.makeText(this, "Botão entrar clicado!", Toast.LENGTH_SHORT).show()

            "tv_forgot_password" ->
                Toast.makeText(this, "Esqueceu a senha?", Toast.LENGTH_SHORT).show()
        }
    }
}