package com.ales.fittrackmobile.ui.user_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.databinding.ActivityUserPageEditViewBinding
import kotlinx.coroutines.launch
import java.lang.Exception

class UserPageEditView : AppCompatActivity() {

    private lateinit var binding: ActivityUserPageEditViewBinding

    private lateinit var userContext: UserContext

    private lateinit var newName: EditText
    private lateinit var newWeight: EditText
    private lateinit var newHeight: EditText
    private lateinit var newAge: EditText
    private lateinit var newGenre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPageEditViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userContext = UserContext.getInstance()

        title = getString(R.string.title_user_page_edit_view)

        loadUser()

        binding.doneEditButton.setOnClickListener{onDoneButtonClick()}
    }

    private fun onDoneButtonClick() {

        if (!checkValues()) return

        setLoading(true)

        val newUser = userContext.user
        if (newName.text.isNotEmpty()) newUser.name = newName.text.toString()
        if (newWeight.text.isNotEmpty()) newUser.weight = newWeight.text.toString().toDouble()
        if (newHeight.text.isNotEmpty()) newUser.height = newHeight.text.toString().toInt()
        if (newAge.text.isNotEmpty()) newUser.age = newAge.text.toString().toInt()
        if (newGenre.text.isNotEmpty()) newUser.genre = newGenre.text.toString()
        userContext.user = newUser

        saveUser()
    }

    private fun saveUser() {
        lifecycleScope.launch {
            try {
                userContext.updateUser()
                finish()
                //startActivity(Intent(this@UserPageEditView, HomeActivity::class.java))
            } catch (e: Exception) {
                Toast.makeText(
                    this@UserPageEditView, "Error Updating", Toast.LENGTH_LONG).show()
            }
            setLoading(false)
        }
    }

    private fun checkValues(): Boolean {
        try {
            newWeight.text.toString().toDouble()
            newHeight.text.toString().toInt()
            newAge.text.toString().toInt()
        } catch (e: NumberFormatException) {
            Toast.makeText(
                this@UserPageEditView, "Incorrect Values", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun loadUser() {
        newName = binding.nameValueEdit
        newHeight = binding.heightValueEdit
        newWeight = binding.weightValueEdit
        newGenre = binding.genreValueEdit
        newAge = binding.ageValueEdit

        newName.setText(userContext.user.name)
        newHeight.setText(userContext.user.height.toString())
        newWeight.setText(userContext.user.weight.toString())
        newGenre.setText(userContext.user.genre)
        newAge.setText(userContext.user.age.toString())
    }

    private fun setLoading(isLoading: Boolean) {
        binding.doneEditButton.isEnabled = !isLoading
        binding.nameValueEdit.isEnabled = !isLoading
        binding.weightValueEdit.isEnabled = !isLoading
        binding.heightValueEdit.isEnabled = !isLoading
        binding.ageValueEdit.isEnabled = !isLoading
        binding.genreValueEdit.isEnabled = !isLoading
        if (isLoading) binding.progressIndicator.show()
        else binding.progressIndicator.hide()
    }

}