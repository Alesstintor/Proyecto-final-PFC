package com.ales.fittrackmobile.api

import android.util.Log
import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.AuthenticationResponse
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Exception

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
                    Log.i("USERDATA", "Fetch Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("USERDATA", "Fetch Unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("USERDATA", "Error on Fetch")
                Result.failure(e)
            }
        }
    }

    suspend fun updateUser(user: User): Result<User?>{
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.updateUser(user).execute()
                if (response.isSuccessful) {
                    Log.i("USERUPDATE", "Patch Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("USERUPDATE", "Patch Unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("USERUPDATE", "Error on Patch")
                Result.failure(e)
            }
        }
    }

    suspend fun refreshToken(): Result<AuthenticationResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.refreshToken().execute()
                if (response.isSuccessful) {
                    Log.i("TOKEN", "Token refreshed successfully")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("TOKEN", "Token refresh unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("TOKEN", "Error on token refresh")
                Result.failure(e)
            }
        }
    }

    suspend fun fetchExercisesData(): Result<List<Exercise>?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.fetchAllExercises().execute()
                if (response.isSuccessful) {
                    Log.i("EXERCISES", "Fetch Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("EXERCISES", "Fetch unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("EXERCISES", "Error on Fetch")
                Result.failure(e)
            }
        }
    }

    suspend fun fetchRoutinesData(): Result<List<Routine>?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.fetchAllRoutines().execute()
                if (response.isSuccessful) {
                    Log.i("ROUTINES", "Fetch Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("ROUTINES", "Fetch unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("ROUTINES", "Error on Fetch")
                Result.failure(e)
            }
        }

    }

    suspend fun saveRoutine(routine: Routine): Result<Routine?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.saveRoutine(routine).execute()
                if (response.isSuccessful) {
                    Log.i("ROUTINE", "Saving Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("ROUTINE", "Saving unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("ROUTINE", "Error on Saving")
                Result.failure(e)
            }
        }
    }

    suspend fun saveExercise(exercise: Exercise): Result<Exercise?> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiAccess.saveExercise(exercise).execute()
                if (response.isSuccessful) {
                    Log.i("EXERCISE", "Saving Successful")
                    val data = response.body()
                    Result.success(data)
                } else {
                    Log.i("EXERCISE", "Saving unsuccessful")
                    Result.failure(Exception(response.message()))
                }
            } catch (e: Exception) {
                Log.e("EXERCISE", "Error on Saving")
                Log.e("EXERCISE", e.stackTraceToString())
                Result.failure(e)
            }
        }
    }
//    fun updateUser(user: User) {
//        apiAccess.updateUser(user).enqueue(object: Callback<User> {
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                try {
//
//                    if (!response.isSuccessful) return
//
//                    response.body() ?: User()
//
//                    Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()
//
//
//                } catch (e: Exception) {
//                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<User>, t: Throwable) {
//                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
