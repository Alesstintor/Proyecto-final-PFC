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

        var username = binding.usernameInput.text.toString()
        var password = binding.passwordInput.text.toString()

        var intent = Intent(this@LoginActivity, MainActivity::class.java)

        intent.putExtra("USERNAME", username)
        intent.putExtra("PASSWORD", password)

        startActivity(intent)

    }


}