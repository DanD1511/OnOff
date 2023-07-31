package com.dand0129.onoff

import CircularSlider
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dand0129.onoff.ui.theme.OnOffTheme
import com.dand0129.onoff.ui_app.UI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uI = UI()
        setContent {
            OnOffTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularSlider(
                            modifier = Modifier.size(200.dp),
                        )
                    }
                }
            }
        }
    }
}
