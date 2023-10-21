package com.pruebita.mydailyfisiapp.ui.screens.schedule

import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Preview
@Composable
fun DrawImageWithTextOnZoomableImage() {
    val maxScale = 2.0f
    val minScale = 1.0f

    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

    val painter = rememberImagePainter(data = "https://dfapruebaf.blob.core.windows.net/mapas/piso_1.png")

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        val newScale = (scale * zoom).coerceIn(minScale, maxScale)

                        val maxOffsetX = (200.dp.toPx() * (newScale - 1)).coerceAtLeast(0f)
                        val maxOffsetY = (200.dp.toPx() * (newScale - 1)).coerceAtLeast(0f)

                        val newOffset = Offset(
                            x = (offset.x + pan.x * scale).coerceIn(-maxOffsetX, maxOffsetX),
                            y = (offset.y + pan.y * scale).coerceIn(-maxOffsetY, maxOffsetY)
                        )

                        scale = newScale
                        offset = newOffset
                    }
                }
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    ),
                onDraw = {


                    // Dibuja el texto sobre la imagen
                    val text = "Texto sobre la imagen"
                    val textSize = 20f
                    val textColor = Color.White
                    val textPosition = Offset(50f, 150f) // Posición del texto

                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        paint.color = textColor.toArgb()
                        paint.textSize = textSize
                        paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)

                        canvas.nativeCanvas.drawText(
                            text,
                            textPosition.x,
                            textPosition.y,
                            paint
                        )
                    }
                }
            )
        }
    }
}