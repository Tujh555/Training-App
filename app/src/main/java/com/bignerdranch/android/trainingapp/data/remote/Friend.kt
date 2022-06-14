package com.bignerdranch.android.trainingapp.data.remote

import com.google.gson.annotations.SerializedName
import java.util.*

data class Friend(
    val id: String,
    @SerializedName("createdAt") val accountCreationDate: Date,
    val name: String,
    val avatar: String,
)