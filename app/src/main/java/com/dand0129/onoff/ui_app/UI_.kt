package com.dand0129.onoff.ui_app

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainButton() {
    OutlinedButton(
        onClick = { },
        border = BorderStroke(
            width = 2.dp,
            brush = Brush.horizontalGradient(
                listOf(
                    Color(0xFFe6f4fc),
                    Color(0xFFddc9fe)
                )
            )
        ),

    ) {
        Text(
            "Change Power",
            color = Color.White,
            fontSize = 30.sp
        )
    }
}
