package com.example.avantsoft.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.avantsoft.data.api.Service
import com.example.avantsoft.data.database.UserDataBase
import com.example.avantsoft.domain.model.ApiResponse
import com.example.avantsoft.domain.model.UserDataBaseModel
import com.example.avantsoft.data.api.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UiRepository(context: Context) {

    private val remote = Client.getService(Service::class.java)
    private val userDataBase = UserDataBase.getDataBase(context).userDAO()

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UiRepository", Context.MODE_PRIVATE)

    fun getUsersAndInsertIntoDatabase() {
        val isDataInserted = sharedPreferences.getBoolean("data_inserted", false)

        if (!isDataInserted) {
            val call = remote.getUsers()

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful) {
                        val usersResponse = response.body()
                        val users = usersResponse?.users ?: emptyList()

                        if (users.isNotEmpty()) {
                            val existingUsers = userDataBase.getAllUser()
                            if (existingUsers.isNotEmpty()) {
                                for (user in users) {
                                    val userModel = UserDataBaseModel(
                                        id = user.id,
                                        name = user.name,
                                        age = user.age.toString(),
                                        email = user.email
                                    )
                                    userDataBase.update(userModel)
                                }
                            }else {
                                for (user in users) {
                                    val userModel = UserDataBaseModel(
                                        id = user.id,
                                        name = user.name,
                                        age = user.age.toString(),
                                        email = user.email
                                    )
                                    userDataBase.insert(userModel)
                                }
                            }

                        }

                        sharedPreferences.edit().putBoolean("data_inserted", true).apply()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    // Trate o erro de acordo com a sua necessidade
                }
            })
        }
    }
}
