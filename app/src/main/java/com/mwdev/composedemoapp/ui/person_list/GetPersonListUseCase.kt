package com.mwdev.composedemoapp.ui.person_list

import com.mwdev.composedemoapp.model.BaseUseCaseWithoutParams
import com.mwdev.composedemoapp.model.Person
import com.mwdev.composedemoapp.model.State
import com.mwdev.composedemoapp.repo.PersonRemoteRepo
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import javax.inject.Inject

class GetPersonListUseCase @Inject constructor(val repo: PersonRemoteRepo): BaseUseCaseWithoutParams<State<List<Person>>> {

    override suspend fun run(): State<List<Person>> {
        return repo.getPersonList().let {apiResponse ->
            when(apiResponse){
                is ApiResponse.Success-> State.Success(apiResponse.data)
                is ApiResponse.Failure.Error -> State.Error(Exception(apiResponse.errorBody?.toString()))
                is ApiResponse.Failure.Exception -> State.Error(apiResponse.exception)
            }
        }
    }
}