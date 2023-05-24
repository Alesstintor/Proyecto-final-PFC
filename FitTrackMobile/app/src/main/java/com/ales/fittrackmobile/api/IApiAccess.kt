package com.ales.fittrackmobile.api

import com.ales.fittrackmobile.entities.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiAccess {
    @GET("/users/{id}")
    fun find(@Path("id") id: Long): Call<User>
}