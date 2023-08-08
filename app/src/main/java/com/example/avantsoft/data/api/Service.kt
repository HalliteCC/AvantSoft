package com.example.avantsoft.data.api

import com.example.avantsoft.domain.model.ApiResponse
import com.example.avantsoft.utils.Const
import retrofit2.Call
import retrofit2.http.GET


interface Service {

    @GET(Const.CLIENT.USER)
    fun getUsers() : Call<ApiResponse>
}