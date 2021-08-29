package com.mwdev.composedemoapp.ui.person_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.mwdev.composedemoapp.model.Person
import com.mwdev.composedemoapp.model.State

@Composable
fun PersonListScreen(navController: NavController, viewModel: PersonViewModel) {
    PersonList(viewModel = viewModel)
}


@Composable
fun PersonList(viewModel: PersonViewModel) {
    val viewState by remember(viewModel) { viewModel.personFlow }.collectAsState()
    val data = (viewState as? State.Success)?.data ?: emptyList()
    LazyColumn {
        items(
            items = data,
            itemContent = {
                PersonListItem(person = it)
            })
    }
}

@Composable
fun PersonListItem(person: Person) {
    Row {
        Column {
            Text(text = "${person.firstName} ${person.lastName}")
        }
    }
}