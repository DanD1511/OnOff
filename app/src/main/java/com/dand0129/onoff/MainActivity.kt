package com.dand0129.onoff


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dand0129.onoff.Slider.CustomCircularProgressIndicator
import com.dand0129.onoff.ui.theme.OnOffTheme
import com.dand0129.onoff.ui_app.MainButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnOffTheme {
                Main()
            }
        }
    }
}

@Composable

fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff2a2a2a)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Spacer(modifier = Modifier.height(80.dp))

            CustomCircularProgressIndicator(
                modifier = Modifier
                    .size(250.dp)
                    .background(Color(0xff2a2a2a)),
                initialValue = 50,
                primaryColor = Color.Yellow,
                secondaryColor = Color.Gray,
                circleRadius = 230f,
                onPositionChange = { position ->
                    //{
                // *TODO*
                //  }
                }
            )

        Spacer(modifier = Modifier.height(80.dp))
        MainButton()
    }
}
