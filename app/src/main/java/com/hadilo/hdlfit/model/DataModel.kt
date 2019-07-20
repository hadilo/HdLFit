package com.hadilo.hdlfit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

@Parcelize
data class DataModel(
    var movementName: String? = null,
    var set: Int? = null,
    var repetition: Int? = null
): Parcelable