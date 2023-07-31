package com.dand0129.onoff.Slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Slider() {
    @Composable
    fun SliderExample() {
        var sliderPosition by remember { mutableStateOf(0f) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Slider Value: %.2f".format(sliderPosition),
                fontSize = 20.sp,
                color = Color.Black
            )

            Slider(
                value = sliderPosition,
                onValueChange = { newValue ->
                    sliderPosition = newValue
                }
            )
        }
    }
}