package com.ales.fittrackmobile.context
import androidx.lifecycle.ViewModel
import com.ales.fittrackmobile.entities.User

class UserContext: ViewModel() {

    var user: User = User()

    companion object {
        @Volatile
        private var instance: UserContext? = null

        fun getInstance(): UserContext {
            return instance ?: synchronized(this) {
                instance ?: UserContext().also { instance = it }
            }
        }
    }
}