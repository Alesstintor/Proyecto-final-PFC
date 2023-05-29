package com.ales.fittrackmobile.context
import androidx.lifecycle.ViewModel
import com.ales.fittrackmobile.api.ApiManager
import com.ales.fittrackmobile.entities.Record
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.RegisterRequest

class UserContext: ViewModel() {

    var user: User = User()
    var recordList: List<Record>? = null
    private val apiManager = ApiManager.getInstance()
    var token: String = ""



    companion object {
        @Volatile
        private var instance: UserContext? = null

        fun getInstance(): UserContext {
            return instance ?: synchronized(this) {
                instance ?: UserContext().also { instance = it }
            }
        }
    }

    suspend fun updateUser(newUser: User) {
        val newUserData = apiManager.updateUser(newUser).getOrThrow()
        if (newUserData != null) {
            user = newUserData
        }
    }

    suspend fun fetchUserData() {
        val fetchedUser = apiManager.fetchUserData().getOrThrow()
        if (fetchedUser != null) {
            user = fetchedUser
        }
    }

    suspend fun login(authenticationRequest: AuthenticationRequest) {
        val authResponse = apiManager.userLogin(authenticationRequest).getOrThrow()
        if (authResponse != null) {
            token = authResponse.token
        }
    }

    suspend fun register(registerRequest: RegisterRequest) {
        val authResponse = apiManager.userRegister(registerRequest).getOrThrow()
        if (authResponse != null) {
            token = authResponse.token
        }
    }

    fun isUserLogged(): Boolean {
        return token.isNotEmpty()
    }
}