package com.ales.fittrackmobile.api

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.AuthenticationResponse
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ApiManager {

    companion object {
        @Volatile
        private var instance: ApiManager? = null

        fun getInstance(): ApiManager {
            return instance ?: synchronized(this) {
                instance ?: ApiManager().also { instance = it }
            }
        }
    }

    private val retrofit: Retrofit = Retrofit.Builder()
                                        .baseUrl("http://144.24.197.53:8080")
                                        .client(HttpClient().client)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()

    private val apiAccess: ApiAccess = retrofit.create(ApiAccess::class.java)

    fun updateUser(context: Context, user: User) {
        apiAccess.updateUser(user).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                try {

                    if (!response.isSuccessful) return

                    response.body() ?: User()

                    Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()


                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    suspend fun userLogin(authenticationRequest: AuthenticationRequest): Result<AuthenticationResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.userLogin(authenticationRequest).execute()
                if (response.isSuccessful) {
                    Log.i("LOGIN", "Login Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("LOGIN", "Login Unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("LOGIN", "Error on Login")
                Result.failure(e)
            }
        }
    }

    suspend fun userRegister(registerRequest: RegisterRequest): Result<AuthenticationResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.userRegister(registerRequest).execute()
                if (response.isSuccessful) {
                    Log.i("REGISTER", "Register Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("REGISTER", "Register Unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("REGISTER", "Error on Register")
                Result.failure(e)
            }
        }
    }

    suspend fun fetchUserData(): Result<User?>{
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.fetchUserData().execute()
                if (response.isSuccessful) {
                    Log.i("FETCH", "Fetch Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("FETCH", "Fetch Unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("FETCH", "Error on Fetch")
                Log.e("FETCH", e.message.toString() + e.stackTraceToString())
                Result.failure(e)
            }
        }
    }
}
