package com.ales.fittrackmobile.context
import androidx.lifecycle.ViewModel
import com.ales.fittrackmobile.api.ApiManager
import com.ales.fittrackmobile.entities.Record
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class UserContext: ViewModel() {

    var user: User = User()
    var recordList: List<Record>? = null
    private val apiManager = ApiManager.getInstance()
    var token: String = ""
    private var tokenCreation = Date()



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
        chechToken()
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
            tokenCreation = Date()
        }
    }

    suspend fun register(registerRequest: RegisterRequest) {
        val authResponse = apiManager.userRegister(registerRequest).getOrThrow()
        if (authResponse != null) {
            token = authResponse.token
            tokenCreation = Date()
        }
    }

    suspend private fun refreshToken() {
        val authResponse = apiManager.refreshToken().getOrThrow()
        if (authResponse != null) {
            token = authResponse.token
            tokenCreation = Date()
        }
    }

    private fun chechToken() {
        GlobalScope.launch(Dispatchers.IO) {
            if (Date().time - tokenCreation.time > 2_400_000) {
                refreshToken()
            }
        }
    }
}