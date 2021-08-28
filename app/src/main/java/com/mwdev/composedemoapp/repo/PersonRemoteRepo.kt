package com.mwdev.composedemoapp.repo

import com.mwdev.composedemoapp.model.Person
import com.skydoves.sandwich.ApiResponse

interface PersonRemoteRepo {

    suspend fun getPersonList(): ApiResponse<List<Person>>
}