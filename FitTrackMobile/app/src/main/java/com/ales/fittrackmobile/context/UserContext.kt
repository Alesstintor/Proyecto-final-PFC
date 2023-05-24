package com.ales.fittrackmobile.context
import android.content.Context
import androidx.lifecycle.ViewModel
import com.ales.fittrackmobile.api.ApiManager
import com.ales.fittrackmobile.entities.Record
import com.ales.fittrackmobile.entities.User

class UserContext: ViewModel() {

    var user: User = User()
    var recordList: List<Record>? = null
    private val apiManager = ApiManager.getInstance()

    companion object {
        @Volatile
        private var instance: UserContext? = null

        fun getInstance(): UserContext {
            return instance ?: synchronized(this) {
                instance ?: UserContext().also { instance = it }
            }
        }
    }

    fun persistUserData(context: Context) {
        apiManager.updateUser(context, user)
    }

    fun fetchUserData(context: Context) {
        apiManager.findUser(context)
    }

    fun findRecords(context: Context) {
        apiManager.findRecords(context, user.id)
    }
}