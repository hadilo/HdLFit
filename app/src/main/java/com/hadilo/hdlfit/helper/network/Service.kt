package com.hadilo.hdlfit.helper.network

import com.google.gson.JsonObject
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property
import com.hadilo.hdlfit.model.pojo.login.Login
import com.hadilo.hdlfit.model.pojo.login.LoginRequest
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @POST(LOGIN)
    fun login(@Body loginRequest: LoginRequest): Call<Login>

    @GET(DATA_MOVEMENT)
    fun getMovement(): Call<MutableList<Movement>>

    @POST(DATA_MOVEMENT)
    fun postMovement(@Body movement: Movement): Call<Movement>

    @PUT("$DATA_MOVEMENT/{parentObjectId}/{relationName}")
    fun postMovementRelation(@Body jsonObject: JsonObject, // { "objectId": "52E93211-0629-8EBB-FF10-E8C374166D00" }
                             @Path("parentObjectId") parentObjectId: String,
                             @Path("relationName") relationName: String
                             ): Call<Int>

    @POST(DATA_PROPERTY)
    fun postProperty(@Body property: Property): Call<Property>
}