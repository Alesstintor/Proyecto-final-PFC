package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.databinding.ActivityUserPageEditViewBinding

class UserPageEditView : AppCompatActivity() {

    private lateinit var binding: ActivityUserPageEditViewBinding
    private lateinit var doneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPageEditViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doneButton.setOnClickListener{onDoneButtonClick()}
    }

    fun onDoneButtonClick() {
        val intent = Intent(this@UserPageEditView, HomeActivity::class.java)
        startActivity(intent)
    }

}