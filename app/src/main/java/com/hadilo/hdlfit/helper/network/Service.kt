package com.hadilo.hdlfit.helper.network

import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.pojo.login.Login
import com.hadilo.hdlfit.model.pojo.login.LoginRequest
import retrofit2.Call
import retrofit2.http.*


interface Service {

    @POST(LOGIN)
    fun login(@Body loginRequest: LoginRequest): Call<Login>

    @GET(DATA_MOVEMENT)
    fun getMovement(): Call<MutableList<Movement>>

}