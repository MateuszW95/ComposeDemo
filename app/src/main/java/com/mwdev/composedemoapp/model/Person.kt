package com.mwdev.composedemoapp.model


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("dob")
    val dob: String?, // 1990-06-08
    @SerializedName("email")
    val email: String?, // clurriman0@skyrock.com
    @SerializedName("first_name")
    val firstName: String?, // Calv
    @SerializedName("id")
    val id: String?, // 4becaca6-a768-42c0-8084-74f28216151e
    @SerializedName("last_name")
    val lastName: String?, // Lurriman
    @SerializedName("photo")
    val photo: String? // http://dummyimage.com/100x100.png/cc0000/ffffff
)