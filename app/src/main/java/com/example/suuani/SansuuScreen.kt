package com.example.suuani

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO : 以下の関数名を「AreaOfRectangle」に修正して画面遷移先で表示する。
@Composable
fun SansuuScreen() {
    var width by remember { mutableFloatStateOf(5f) }
    var height by remember { mutableFloatStateOf(3f) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("長方形の面積: 面積 = 幅 × 高さ")

        Slider(
            value = width,
            onValueChange = { width = it },
            valueRange = 1f..10f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("幅: $width")

        Slider(
            value = height,
            onValueChange = { height = it },
            valueRange = 1f..10f,
            steps = 10,
            modifier = Modifier.fillMaxWidth()
        )
        Text("高さ: $height")

        Text("面積: ${width * height}")

        Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
            val widthPx = width * 30f
            val heightPx = height * 30f

            drawRect(
                color = androidx.compose.ui.graphics.Color.Gray,
                size = androidx.compose.ui.geometry.Size(widthPx, heightPx)
            )
        }
    }
}