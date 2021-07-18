package com.mwdev.composedemoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberImagePainter
import com.mwdev.composedemoapp.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                //NewsStory()
                header()
            }
        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @Composable
    fun NewsStory() {
        MaterialTheme {
            val typography = MaterialTheme.typography
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = rememberImagePainter(
                        data = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png",
                        builder = {
                            placeholder(R.drawable.ic_launcher_background)
                            error(R.drawable.ic_launcher_foreground)
                        }
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .shadow(elevation = 5.dp),
                    contentDescription = "null",
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("A day in Shark Fin Cove", style = typography.h6)
                Text("Davenport, California", style = typography.body2)
                Text("December 2018", style = typography.body2)
            }
        }
    }


    @Composable
    fun header() {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.blue
                            )
                        )
                    )
            ) {

            }
        }
    }


}