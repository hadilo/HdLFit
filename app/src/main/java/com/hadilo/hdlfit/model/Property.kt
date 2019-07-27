package com.hadilo.hdlfit.model

import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Property(

	@field:SerializedName("set")
	var set: Int? = null,

	@field:SerializedName("load")
	var load: Int? = null,

	@field:SerializedName("created")
	var created: Long? = null,

	@field:SerializedName("___class")
	var ___class: String? = null,

	@field:SerializedName("repetition")
	var repetition: Int? = null,

	@field:SerializedName("ownerId")
	var ownerId: String? = null,

	@field:SerializedName("updated")
	var updated: Long? = null,

	@field:SerializedName("objectId")
	var objectId: String? = null
): Parcelable