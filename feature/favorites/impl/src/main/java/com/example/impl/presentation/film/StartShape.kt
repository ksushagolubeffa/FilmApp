package com.example.impl.presentation.film

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarButton(
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    onClick: () -> Unit,
    isColored: Boolean,
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(size),
        colors = ButtonDefaults.buttonColors(backgroundColor =  if (isColored) Color(0xFF653E81) else Color.Transparent)
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val centerX = size.toPx() / 2
            val centerY = size.toPx() / 2
            val radius = size.toPx() / 2 * 0.8f

            rotate(-30f, pivot = center) {
                repeat(5) { index ->
                    val angle = index * 72f
                    val x1 = centerX + radius * kotlin.math.cos(Math.toRadians(angle.toDouble()))
                        .toFloat()
                    val y1 = centerY + radius * kotlin.math.sin(Math.toRadians(angle.toDouble()))
                        .toFloat()

                    val x2 =
                        centerX + radius / 2 * kotlin.math.cos(Math.toRadians((angle + 36).toDouble()))
                            .toFloat()
                    val y2 =
                        centerY + radius / 2 * kotlin.math.sin(Math.toRadians((angle + 36).toDouble()))
                            .toFloat()

                    val x3 =
                        centerX + radius * kotlin.math.cos(Math.toRadians((angle + 72).toDouble()))
                            .toFloat()
                    val y3 =
                        centerY + radius * kotlin.math.sin(Math.toRadians((angle + 72).toDouble()))
                            .toFloat()

                    drawLine(
                        color = Color.Black,
                        start = Offset(x1, y1),
                        end = Offset(x3, y3),
                        strokeWidth = 2f
                    )

                    drawLine(
                        color = Color.Black,
                        start = Offset(x1, y1),
                        end = Offset(x2, y2),
                        strokeWidth = 2f
                    )

                    drawLine(
                        color = Color.Black,
                        start = Offset(x2, y2),
                        end = Offset(x3, y3),
                        strokeWidth = 2f
                    )
                }
            }
        }
    }
}