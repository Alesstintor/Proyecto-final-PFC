package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityUserPageEditViewBinding

class UserPageEditView : AppCompatActivity() {

    private lateinit var binding: ActivityUserPageEditViewBinding
    private lateinit var doneButton: Button

    private val userContext: UserContext = UserContext()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPageEditViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doneButton = binding.doneEditButton

        loadUser()

        doneButton.setOnClickListener{onDoneButtonClick()}
    }

    fun onDoneButtonClick() {
        val intent = Intent(this@UserPageEditView, HomeActivity::class.java)
        startActivity(intent)

        userContext.setUsername(binding.usernameValueEdit.text.toString())
        userContext.setWeight(binding.weightValueEdit.text.toString().toDouble())
        userContext.setHeight(binding.heightValueEdit.text.toString().toDouble())
        userContext.setAge(binding.ageValueEdit.text.toString().toInt())
        userContext.setGenre(binding.genreValueEdit.toString())
    }

    private fun loadUser() {
        binding.usernameValueEdit.setText(userContext.getUsername())
        binding.heightValueEdit.setText(userContext.getHeight().toString())
        binding.weightValueEdit.setText(userContext.getWeight().toString())
        binding.genreValueEdit.setText(userContext.getGenre())
        binding.ageValueEdit.setText(userContext.getAge().toString())
    }

}