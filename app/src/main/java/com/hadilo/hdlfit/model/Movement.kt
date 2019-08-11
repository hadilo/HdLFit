package com.hadilo.hdlfit.model

import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import com.hadilo.hdlfit.utils.widget.spinner.SpinnerTextInputLayout
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movement(

	@field:SerializedName("created")
	var created: Long? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("___class")
	var ___class: String? = null,

	@field:SerializedName("property")
	var property: MutableList<Property>? = null,

	@field:SerializedName("ownerId")
	var ownerId: String? = null,

	@field:SerializedName("updated")
	var updated: Long? = null,

	@field:SerializedName("objectId")
	var objectId: String? = null
): Parcelable {
	override fun toString(): String {
		return name!!
	}
}