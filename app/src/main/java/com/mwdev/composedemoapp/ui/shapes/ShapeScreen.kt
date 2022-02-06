package com.mwdev.composedemoapp.ui.shapes

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ShapeScreen(navController: NavController) {
    val sliderValues by remember { mutableStateOf(listOf(0.5f, 0.2f, 0.9f)) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var currentValue by remember {
            mutableStateOf(0f)
        }
        LaunchedEffect(true) {
            sliderValues.onEach {
                currentValue = it
                delay(2000)
            }
        }

        Slider(currentValue, 30.dp)
    }
}


@Composable
fun Slider(leftValue: Float, gapSpace: Dp) {
    val stepAnim: Float by animateFloatAsState(
        targetValue = leftValue,
        animationSpec = tween(
            durationMillis = 3000, // animation duration
            easing = FastOutSlowInEasing // animation easing
        )
    )
    val currentLeftValue:Float= stepAnim
    Canvas(
        modifier = Modifier
            .width(300.dp)
            .height(150.dp)
            .padding(16.dp)
    ) {
        val availableWidth =size.width - gapSpace.value
        val h=60.dp
        val lPath = Path()
        lPath.lineTo(currentLeftValue*availableWidth, 0f)
        lPath.lineTo((currentLeftValue*availableWidth)-gapSpace.value, h.value)
        lPath.lineTo(0f, h.value)
        lPath.lineTo(0f, 0f)
        clipPath(
            path = lPath,
            clipOp = ClipOp.Intersect
        ) {
            drawPath(
                path = lPath,
                brush = SolidColor(Color.LightGray)
            )
            drawRect(
                SolidColor(Color.Green),
                size = Size(
                    size.width,
                    h.value
                )
            )
        }
        val rPath = Path()
        rPath.moveTo((currentLeftValue*availableWidth) + gapSpace.value, 0f)
        rPath.lineTo(size.width, 0f)
        rPath.lineTo(size.width, h.value)
        rPath.lineTo(currentLeftValue*availableWidth, h.value)
        rPath.lineTo((currentLeftValue*availableWidth) + gapSpace.value, 0f)

        clipPath(
            path = rPath,
            clipOp = ClipOp.Intersect
        ) {
            drawPath(
                path = rPath,
                brush = SolidColor(Color.LightGray)
            )
            drawRect(
                SolidColor(Color.Red),
                size = Size(
                    size.width,
                    h.value
                )
            )
        }
    }
}