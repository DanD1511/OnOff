package com.dand0129.onoff


import CircularSlider
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dand0129.onoff.ui.theme.OnOffTheme
import com.dand0129.onoff.ui_app.MainButton
import com.dand0129.onoff.ui_app.UI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uI = UI()
        setContent {
            OnOffTheme {
                Main(ui = UI())
            }

        }
    }
}

@Composable

fun Main(ui: UI) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff2a2a2a)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Power regulation",
            style = TextStyle(fontSize = 50.sp),
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush =
                        Brush.horizontalGradient(listOf(Color(0xFFe6f3fc), Color(0xFFdfc9ff)))
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                }
        )
        Spacer(modifier = Modifier.height(80.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.4f)
                .background(
                    color = Color(0xffe6f5fc),
                    shape = RoundedCornerShape(30.dp)
                )
                .border(
                    1.dp,
                    Color.White,
                    shape = RoundedCornerShape(30.dp)
                ),
        ) {
            CircularSlider(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
            )
        }
        Spacer(modifier = Modifier.height(80.dp))
        MainButton()
    }
}
