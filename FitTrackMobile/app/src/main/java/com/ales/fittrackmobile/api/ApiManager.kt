package com.ales.fittrackmobile.api

import android.content.Context
import android.widget.Toast
import com.ales.fittrackmobile.context.UserContext
import com.ales.fittrackmobile.entities.User
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

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
                                        .baseUrl("")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()

    private val apiAccess: ApiAccess = retrofit.create(ApiAccess::class.java)

    fun fetchUser(context: Context) {
        apiAccess.findUser(1).enqueue(object: Callback<User> {
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

    fun updateUser(context: Context, user: User) {
        apiAccess.updateUser(user).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                try {

                    if (!response.isSuccessful) return

                    response.body() ?: User()

                    Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()


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