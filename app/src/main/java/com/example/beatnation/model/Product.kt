package com.example.beatnation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val name: String,
    val price: Double,
    val rating: Double,
    val imageResId: Int
) : Parcelable

