package com.ales.fittrackmobile.api

import android.util.Log
import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.AuthenticationResponse
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import com.ales.fittrackmobile.exceptions.FetchUnsuccessfulException
import com.ales.fittrackmobile.exceptions.LoginUnsuccessfulException
import com.ales.fittrackmobile.exceptions.PatchUnsuccessfulException
import com.ales.fittrackmobile.exceptions.PutUnsuccessfulException
import com.ales.fittrackmobile.exceptions.RegisterUnsuccessfulException
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
                                        .baseUrl("http://x.x.x.x:8080") // Change this
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
                    Result.failure(LoginUnsuccessfulException(response.message()))
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
                    Result.failure(RegisterUnsuccessfulException(response.message()))
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
                    Log.w("USERDATA", "Fetch Unsuccessful")
                    Result.failure(FetchUnsuccessfulException(response.message()))
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
                    Log.w("USERUPDATE", "Patch Unsuccessful")
                    Result.failure(PatchUnsuccessfulException(response.message()))
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
                    Log.w("TOKEN", "Token refresh unsuccessful")
                    Result.failure(FetchUnsuccessfulException(response.message()))
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
                    Log.w("EXERCISES", "Fetch unsuccessful")
                    Result.failure(FetchUnsuccessfulException(response.message()))
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
                    Log.w("ROUTINES", "Fetch unsuccessful")
                    Result.failure(FetchUnsuccessfulException(response.message()))
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
                    Log.w("ROUTINE", "Saving unsuccessful")
                    Result.failure(PutUnsuccessfulException(response.message()))
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
                    Log.w("EXERCISE", "Saving unsuccessful")
                    Result.failure(PutUnsuccessfulException(response.message()))
                }
            } catch (e: Exception) {
                Log.e("EXERCISE", "Error on Saving")
                Log.e("EXERCISE", e.stackTraceToString())
                Result.failure(e)
            }
        }
    }
}
