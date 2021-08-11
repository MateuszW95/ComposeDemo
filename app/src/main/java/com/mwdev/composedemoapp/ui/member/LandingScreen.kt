package com.mwdev.composedemoapp.ui.member

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mwdev.composedemoapp.R

@Composable
fun LandingScreen(navigation: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopView()
        Spacer(modifier = Modifier.size(20.dp))
        FullButton(text = "Sign In") {

        }
        Spacer(modifier = Modifier.size(20.dp))
        FullButton(text = "Sign Up") {

        }
    }
}

@Composable
fun TopView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .rotate(20.0f)
                .size(100.dp)
                .background(color = Color.Black, shape = CircleShape)
                .clip(CircleShape),
            painter = rememberImagePainter("https://placekitten.com/100/100"),
            contentDescription = "Logo"
        )
    }

}

@Composable
fun FullButton(text: String, onClickAction: () -> Unit) {
    Button(
        onClick = {
            onClickAction()
        },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color.Red,
        ),
        shape = RoundedCornerShape(percent = 50),
        modifier = Modifier
            .shadow(0.1.dp, RoundedCornerShape(percent = 50))
            .padding(5.dp)
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopViewPreview() {
    TopView()
}

@Preview(showBackground = true)
@Composable
fun SignInFullButtonPreview() {
    FullButton("SignIn") {

    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    TopView()
}