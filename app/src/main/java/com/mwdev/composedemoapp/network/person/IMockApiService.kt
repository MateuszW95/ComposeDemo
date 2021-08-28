package com.mwdev.composedemoapp.network.person

import com.mwdev.composedemoapp.model.Person
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface IMockApiService {

    @GET("v3/15f5298d-32de-4592-8fb2-8cc53d3f406f")
    suspend fun fetchPersonList(): ApiResponse<List<Person>>
}