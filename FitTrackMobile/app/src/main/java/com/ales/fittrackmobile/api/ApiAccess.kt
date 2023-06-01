package com.ales.fittrackmobile.api

import com.ales.fittrackmobile.entities.Exercise
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.entities.User
import com.ales.fittrackmobile.entities.auth.AuthenticationRequest
import com.ales.fittrackmobile.entities.auth.AuthenticationResponse
import com.ales.fittrackmobile.entities.auth.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiAccess {

    @POST("/auth/login")
    fun userLogin(@Body authRequest: AuthenticationRequest): Call<AuthenticationResponse>

    @POST("/auth/register")
    fun userRegister(@Body registerRequest: RegisterRequest): Call<AuthenticationResponse>

    @PATCH("/users/myUser")
    fun updateUser(@Body user: User): Call<User>

    @GET("/users/myUser")
    fun fetchUserData(): Call<User>

    @GET("/auth/revalidate")
    fun refreshToken(): Call<AuthenticationResponse>

    @GET("/exercises")
    fun fetchAllExercises(): Call<List<Exercise>>

    @GET("/routines")
    fun fetchAllRoutines(): Call<List<Routine>>

    @POST("/routines")
    fun saveRoutine(@Body routine: Routine): Call<Routine>

    @POST("/exercises")
    fun saveExercise(@Body exercise: Exercise): Call<Exercise>


}