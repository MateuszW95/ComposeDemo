package com.mwdev.composedemoapp.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mwdev.composedemoapp.R

@Composable
fun MainScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.PersonListScreen.route)
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Blue
            )
        ) {
            Text(
                text = stringResource(id = R.string.go_to_list),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(0.dp, 10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.LandingScreen.route)
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Red,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.go_to_landing),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(0.dp, 10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.ShapeScreen.route)
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Green,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.go_to_shapes),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(0.dp, 10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.CalendarScreen.route)
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Red,
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.go_to_calendar),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}