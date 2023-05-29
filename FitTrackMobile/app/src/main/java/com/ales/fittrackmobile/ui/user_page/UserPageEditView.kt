package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityUserPageEditViewBinding

class UserPageEditView : AppCompatActivity() {

    private lateinit var binding: ActivityUserPageEditViewBinding

    private lateinit var userContext: UserContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPageEditViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        loadUser()

        binding.doneEditButton.setOnClickListener{onDoneButtonClick()}
    }

    private fun onDoneButtonClick() {
        userContext.user.username = binding.usernameValueEdit.text.toString()
        userContext.user.weight = binding.weightValueEdit.text.toString().toDouble()
        userContext.user.height = binding.heightValueEdit.text.toString().toDouble()
        userContext.user.age = binding.ageValueEdit.text.toString().toInt()
        userContext.user.genre = binding.genreValueEdit.text.toString()

        userContext.persistUserData(this@UserPageEditView)

        val intent = Intent(this@UserPageEditView, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun loadUser() {
        binding.usernameValueEdit.setText(userContext.user.username)
        binding.heightValueEdit.setText(userContext.user.height.toString())
        binding.weightValueEdit.setText(userContext.user.weight.toString())
        binding.genreValueEdit.setText(userContext.user.genre)
        binding.ageValueEdit.setText(userContext.user.age.toString())
    }

}