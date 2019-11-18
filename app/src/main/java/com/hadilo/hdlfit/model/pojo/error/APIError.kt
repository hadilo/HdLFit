package com.hadilo.hdlfit.model.pojo.error

import com.google.gson.annotations.SerializedName

data class APIError(

    @field:SerializedName("code")
    var code: String? = null,

    @field:SerializedName("message")
    var message: String? = null
)