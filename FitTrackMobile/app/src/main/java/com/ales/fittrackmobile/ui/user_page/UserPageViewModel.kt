package com.ales.fittrackmobile.ui.user_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserPageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}