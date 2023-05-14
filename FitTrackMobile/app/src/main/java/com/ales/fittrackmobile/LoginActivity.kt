package com.ales.fittrackmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ales.fittrackmobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { onLoginButtonClick() }
    }


    private fun onLoginButtonClick() {

        val username = binding.usernameInput.text.toString()
        val password = binding.passwordInput.text.toString()

        val intent = Intent(this@LoginActivity, HomeActivity::class.java)


        startActivity(intent)

    }


}