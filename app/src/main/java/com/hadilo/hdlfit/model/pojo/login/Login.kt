package com.hadilo.hdlfit.model.pojo.login

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("lastLogin")
	var lastLogin: Long? = null,

	@field:SerializedName("userStatus")
	var userStatus: String? = null,

	@field:SerializedName("created")
	var created: Long? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("___class")
	var ___class: String? = null,

	@field:SerializedName("blUserLocale")
	var blUserLocale: String? = null,

	@field:SerializedName("user-token")
	var userToken: String? = null,

	@field:SerializedName("socialAccount")
	var socialAccount: String? = null,

	@field:SerializedName("ownerId")
	var ownerId: String? = null,

	@field:SerializedName("updated")
	var updated: Long? = null,

	@field:SerializedName("email")
	var email: String? = null,

	@field:SerializedName("objectId")
	var objectId: String? = null
)