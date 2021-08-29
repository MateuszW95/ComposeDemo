package com.mwdev.composedemoapp.ui.person_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mwdev.composedemoapp.model.Person
import com.mwdev.composedemoapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(val getPersonListUseCase: GetPersonListUseCase) : ViewModel() {

    val personFlow: StateFlow<State<List<Person>>> = flow {
        emit(State.Loading)
        emit(getPersonListUseCase.run())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),State.Loading)
}