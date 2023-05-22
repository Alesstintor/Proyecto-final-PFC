package com.ales.fittrackmobile.context

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserContext: ViewModel() {

    private val _username: MutableLiveData<String> = MutableLiveData("Guest")

    fun getUsername(): String? = _username.value

    fun setUsername(value: String) {
        _username.value = value
    }

    private val _weight: MutableLiveData<Double> = MutableLiveData(0.0)

    fun getWeight(): Double? = _weight.value

    fun setWeight(value: Double) {
        _weight.value = value
    }

    private val _height: MutableLiveData<Double> = MutableLiveData(0.0)

    fun getHeight(): Double? = _height.value

    fun setHeight(value: Double) {
        _height.value = value
    }

    private val _age: MutableLiveData<Int> = MutableLiveData(0)

    fun getAge(): Int? = _age.value

    fun setAge(value: Int) {
        _age.value = value
    }

    private val _genre: MutableLiveData<String> = MutableLiveData("None")

    fun getGenre(): String? = _genre.value

    fun setGenre(value: String) {
        _genre.value = value
    }
}