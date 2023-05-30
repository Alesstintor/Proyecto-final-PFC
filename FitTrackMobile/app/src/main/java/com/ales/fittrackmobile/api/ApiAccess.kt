package com.ales.fittrackmobile.api

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

    @GET("/auht/revalidate")
    fun refreshToken(): Call<AuthenticationResponse>
}