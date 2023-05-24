package com.ales.fittrackmobile.api

import android.content.Context
import android.widget.Toast
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.entities.User
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ApiDataGetter {

    private val retrofit: Retrofit = Retrofit.Builder()
                                        .baseUrl("")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()

    private val apiAccess: IApiAccess = retrofit.create(IApiAccess::class.java)

    private val call: Call<User> = apiAccess.find(1)

    fun fetchRoutines(context: Context) {
        call.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                try {

                    if (!response.isSuccessful) return

                    val user = response.body() ?: User()

                    UserContext.getInstance().user = user


                } catch (e: java.lang.Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}