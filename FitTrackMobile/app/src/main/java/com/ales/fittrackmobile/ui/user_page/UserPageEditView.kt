package com.ales.fittrackmobile.ui.user_page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ales.fittrackmobile.HomeActivity
import com.ales.fittrackmobile.context.UserContextModel
import com.ales.fittrackmobile.databinding.ActivityUserPageEditViewBinding

class UserPageEditView : AppCompatActivity() {

    private lateinit var binding: ActivityUserPageEditViewBinding
    private lateinit var doneButton: Button

    private val userContextModel: UserContextModel = UserContextModel()

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

        userContextModel.setUsername(binding.usernameValueEdit.text.toString())
        userContextModel.setWeight(binding.weightValueEdit.text.toString().toDouble())
        userContextModel.setHeight(binding.heightValueEdit.text.toString().toDouble())
        userContextModel.setAge(binding.ageValueEdit.text.toString().toInt())
        userContextModel.setGenre(binding.genreValueEdit.toString())
    }

    private fun loadUser() {
        binding.usernameValueEdit.setText(userContextModel.getUsername())
        binding.heightValueEdit.setText(userContextModel.getHeight().toString())
        binding.weightValueEdit.setText(userContextModel.getWeight().toString())
        binding.genreValueEdit.setText(userContextModel.getGenre())
        binding.ageValueEdit.setText(userContextModel.getAge().toString())
    }

}