package com.ales.fittrackmobile.api

import com.ales.fittrackmobile.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiAccess {
    @GET("/users/{id}")
    fun findUser(@Path("id") id: Long): Call<User>

    @POST("/users")
    fun updateUser(@Body user: User): Call<User>
}