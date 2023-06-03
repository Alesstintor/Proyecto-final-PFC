package com.ales.fittrackmobile.context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.ales.fittrackmobile.api.ApiManager
import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Record
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.util.Date

class UserContext: ViewModel() {

    lateinit var sharedPreferences: SharedPreferences
    var user: User = User()
    var recordList: List<Record>? = null
    private val apiManager = ApiManager.getInstance()
    var token: String = ""
    private var tokenCreation = Date()
    var exercisesList: List<Exercise> = ArrayList()
    var routinesList: List<Routine> = ArrayList()
    var digest: MessageDigest = MessageDigest.getInstance("SHA-256")


    companion object {
        @Volatile
        private var instance: UserContext? = null

        fun getInstance(): UserContext {
            return instance ?: synchronized(this) {
                instance ?: UserContext().also { instance = it }
            }
        }
    }

    suspend fun updateUser() {
        val newUserData = apiManager.updateUser(user).getOrThrow()
        if (newUserData != null) {
            user = newUserData
        }
    }

    suspend fun fetchUserData() {
        val fetchedUser = apiManager.fetchUserData().getOrThrow()
        if (fetchedUser != null) {
            user = fetchedUser
        }
        checkToken()
    }

    suspend fun fetchExercisesData() {
        val fetchedData = apiManager.fetchExercisesData().getOrThrow()
        if (fetchedData != null) {
            exercisesList = fetchedData
        }
    }

    suspend fun fetchRoutinesData() {
        val fetchedData = apiManager.fetchRoutinesData().getOrThrow()
        if (fetchedData != null) {
            routinesList = fetchedData
        }
    }

    suspend fun saveRoutine(routine: Routine): Routine {
        val fetchedData = apiManager.saveRoutine(routine).getOrThrow()
        if (fetchedData != null) {
            fetchRoutinesData()
            return fetchedData
        }
        throw Exception("Routine Received is null")
    }

    suspend fun login(authenticationRequest: AuthenticationRequest) {
        authenticationRequest.password = encryptPassword(authenticationRequest.password)
        val authResponse = apiManager.userLogin(authenticationRequest).getOrThrow()
        if (authResponse != null) {
            saveToken(authResponse.token)
        }
    }

    suspend fun register(registerRequest: RegisterRequest) {
        registerRequest.password = encryptPassword(registerRequest.password)
        val authResponse = apiManager.userRegister(registerRequest).getOrThrow()
        if (authResponse != null) {
            saveToken(authResponse.token)
        }
    }

    suspend fun saveExercise(exercise: Exercise): Exercise {
        val fetchedData: Exercise? = apiManager.saveExercise(exercise).getOrThrow()
        if (fetchedData != null) {
            return fetchedData
        }
        throw Exception("Exercise Received is null")
    }

    private suspend fun refreshToken() {
        val authResponse = apiManager.refreshToken().getOrThrow()
        if (authResponse != null) {
            saveToken(authResponse.token)
        }
    }
    suspend fun tokenLogin() {
        refreshToken()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun checkToken() {
        GlobalScope.launch(Dispatchers.IO) {
            if (Date().time - tokenCreation.time > 2_400_000) {
                refreshToken()
            }
        }
    }

    private fun saveToken(newToken: String) {
        token = newToken
        tokenCreation = Date()
        val editor = sharedPreferences.edit()
        editor.putString("authToken", token)
        editor.apply()
    }

    private fun encryptPassword(password: String): String {
        val hasedPassword = digest.digest(password.toByteArray())
        return hasedPassword.joinToString("") { "%02x".format(it) }
    }

    fun doLocalTokenExist(): Boolean {
        val authToken = sharedPreferences.getString("authToken", null)
        if (authToken != null) {
            token = authToken
            return true
        }
        return false
    }

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.putString("authToken", null)
        editor.apply()
    }

}