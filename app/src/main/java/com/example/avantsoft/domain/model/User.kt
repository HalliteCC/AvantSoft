package com.example.avantsoft.domain.model

import com.google.gson.annotations.SerializedName


data class User(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,
    @SerializedName("age")
    var age: Int,
)