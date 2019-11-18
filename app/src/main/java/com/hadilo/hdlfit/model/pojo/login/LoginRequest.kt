package com.hadilo.hdlfit.model.pojo.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("login")
    var username: String,

    @field:SerializedName("password")
    var password: String
)
