package com.ales.fittrackmobile.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityRegisterBinding
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import com.ales.fittrackmobile.ui.MainActivity
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.StringBuilder

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        binding.registerButton.setOnClickListener {onRegisterButtonClick()}
        binding.loginClickable.setOnClickListener{onLoginClick()}
    }

    private fun onLoginClick() {
        startActivity(Intent(this@RegisterActivity, RegisterActivity::class.java))
    }

    private fun onRegisterButtonClick() {

        if(!areFieldsOk()) return

        setLoading(true)

        val name = binding.nameInput.text.toString()
        val surname = binding.surnameInput.text.toString()
        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        val registerRequest = RegisterRequest(name, surname, username, password)

        doRegister(registerRequest)

    }

    private fun doRegister(registerRequest: RegisterRequest) {

        lifecycleScope.launch {
            try {
                userContext.register(registerRequest)
                userContext.fetchUserData()
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity,
                    "Registration Error", Toast.LENGTH_LONG).show()
            }
            setLoading(false)
        }
    }

    private fun setLoading(loading: Boolean) {
        binding.registerButton.isEnabled = !loading
        binding.nameInput.isEnabled = !loading
        binding.surnameInput.isEnabled = !loading
        binding.usernameInput.isEnabled = !loading
        binding.passwordInput.isEnabled = !loading
        if (loading) binding.progressIndicator.show()
        else binding.progressIndicator.hide()
    }

    private fun areFieldsOk(): Boolean {
        var fieldsCorrect = true
        val errorText = StringBuilder()

        if (binding.nameInput.text.isNullOrEmpty()) {
            errorText.append("Name")
            fieldsCorrect =  false
        }
        if (binding.surnameInput.text.isNullOrEmpty()) {
            if (!fieldsCorrect) errorText.append(", Surname")
            else errorText.append("Surname")
            fieldsCorrect =  false
        }
        if (binding.usernameInput.text.isNullOrEmpty()) {
            if (!fieldsCorrect) errorText.append(", Username")
            else errorText.append("Username")
            fieldsCorrect =  false
        }
        if (binding.passwordInput.text.isNullOrEmpty()) {
            if (!fieldsCorrect) errorText.append(" and Password")
            else errorText.append("Password")
            fieldsCorrect = false
        } else {
            val commaIndex = errorText.indexOfLast { it == Char(44) }
            errorText.replace(commaIndex, commaIndex + 1, " and")
        }

        if (!fieldsCorrect) {
            errorText.append(" Cannot be Empty")
            Toast.makeText(this@RegisterActivity, errorText, Toast.LENGTH_LONG).show()

        }


        return fieldsCorrect
    }
}