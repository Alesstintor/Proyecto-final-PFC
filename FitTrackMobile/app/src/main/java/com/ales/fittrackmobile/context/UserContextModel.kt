package com.ales.fittrackmobile.context

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserContextModel: ViewModel() {

    private val _username = MutableLiveData<String>().apply {
        value = "Guest"
    }
    val username: LiveData<String> = _username

    private val _weight = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val weight: LiveData<Double> = _weight

    private val _height = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val height: LiveData<Double> = _height

    private val _age = MutableLiveData<Int>().apply {
        value = 0
    }
    val age: LiveData<Int> = _age


    private val _genre = MutableLiveData<String>().apply {
        value = "None"
    }
    val genre: LiveData<String> = _genre
}