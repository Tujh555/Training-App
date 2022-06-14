package com.bignerdranch.android.trainingapp.data.remote

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email") val userEmail: String,
    @SerializedName("avatar") val photo: String,
    val name: String,
    val friends: List<Friend>,
    val id: String,
)