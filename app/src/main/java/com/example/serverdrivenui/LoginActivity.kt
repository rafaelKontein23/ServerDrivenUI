package com.example.serverdrivenui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var sduiHostController: SDUIHostController

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

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
            "btn_login" ->
                Toast.makeText(this, "Botão entrar clicado!", Toast.LENGTH_SHORT).show()

            "tv_forgot_password" ->
                Toast.makeText(this, "Esqueceu a senha?", Toast.LENGTH_SHORT).show()

            "tv_register" -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}