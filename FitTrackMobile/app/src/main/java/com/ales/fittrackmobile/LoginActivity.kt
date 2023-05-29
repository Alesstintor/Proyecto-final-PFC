package com.ales.fittrackmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityLoginBinding
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.StringBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        binding.loginButton.setOnClickListener { onLoginButtonClick() }

        binding.signInClickable.setOnClickListener { onRegisterClick()}
    }

    private fun onRegisterClick() {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
    }


    private fun onLoginButtonClick() {

        if(!areFieldsOk()) return

        setLoading(true)

        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        val authRequest = AuthenticationRequest(username, password)

        doLogin(authRequest)

    }

    private fun areFieldsOk(): Boolean {
        var fieldsCorrect = true
        val errorText = StringBuilder()

        if (binding.usernameInput.text.isNullOrEmpty()) {
            errorText.append("username")
            fieldsCorrect =  false
        }
        if (binding.passwordInput.text.isNullOrEmpty()) {
            if (!fieldsCorrect) errorText.append(" and password")
            else errorText.append("password")
            fieldsCorrect = false
        }

        if (!fieldsCorrect) {
            errorText.append(" cannot be empty")
            Toast.makeText(this@LoginActivity, errorText, Toast.LENGTH_LONG).show()
        }

        return fieldsCorrect
    }

    private fun doLogin(authRequest: AuthenticationRequest) {

        lifecycleScope.launch {
            try {
                userContext.login(authRequest)
                userContext.fetchUserData()
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity,
                    "Incorrect Credentials", Toast.LENGTH_LONG).show()
            }
            setLoading(false)
        }


    }

    private fun setLoading(loading: Boolean) {
        binding.loginButton.isEnabled = !loading
        binding.usernameInput.isEnabled = !loading
        binding.passwordInput.isEnabled = !loading
        if (loading) binding.progressIndicator.show()
        else binding.progressIndicator.hide()
    }


}