package com.ales.fittrackmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ales.fittrackmobile.databinding.ActivityLoginBinding
import com.ales.fittrackmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setWelcomeText()

    }

    private fun setWelcomeText() {

        var username = intent.getStringExtra("USERNAME")
        var password = intent.getStringExtra("PASSWORD")

        var newWelcomeText = "Hello $username, your password is: $password"

        binding.welcomeText.text = newWelcomeText


    }

}