package com.ales.fittrackmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityRegisterBinding
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import kotlinx.coroutines.launch
import java.lang.Exception

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
                startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(this@RegisterActivity,
                    "Registration Error", Toast.LENGTH_LONG).show()
            }
            setLoading(false)
        }

        //userContext.fetchUserData(this@LoginActivity)
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
}