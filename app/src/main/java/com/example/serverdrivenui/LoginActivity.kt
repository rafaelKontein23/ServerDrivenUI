package com.example.serverdrivenui

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.ds.input.DsInput
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var sduiHostController: SDUIHostController

    private val viewModel: LoginViewModel by viewModels()
    lateinit var container : LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val container = findViewById<LinearLayout>(R.id.sdui_container)

        sduiHostController.setAppTheme(mapOf("#DB0A88" to "#3E4349"))

        sduiHostController.bind(
            lifecycleOwner = this,
            stateFlow = viewModel.uiState,
            container = container,
            onAction = { actionId ->
                handleSduiActions(actionId)
            }
        )
        viewModel.loadScreen()
        sduiHostController.isSuccess.observe(this){
            val input = sduiHostController
                .findViewBySduiId<DsInput>(container, "input_email")

            input?.error = "Email inválido"
        }


    }

    private fun handleSduiActions(actionId: String) {
        when (actionId) {
            "btn_login" ->{
               val  inputEmail =  container.findViewWithTag<DsInput>("input_email")
               val  inputPassword =  container.findViewWithTag<DsInput>("input_password")

            }


            "tv_forgot_password" -> {
                val intent = Intent(this, ForgotPasswordActivity::class.java)
                startActivity(intent)
            }

            "tv_register" -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}