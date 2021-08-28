package com.mwdev.composedemoapp.repo

import com.mwdev.composedemoapp.model.Person
import com.mwdev.composedemoapp.network.person.IMockApiService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class PersonRemoteRepoImpl @Inject constructor(val apiService: IMockApiService): PersonRemoteRepo {

    override suspend fun getPersonList(): ApiResponse<List<Person>> {
        return apiService.fetchPersonList()
    }


}