package com.pruebita.mydailyfisiapp.ui.screens.schedule

import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Paint
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.rememberImagePainter
import com.pruebita.mydailyfisiapp.R
@Composable
@Preview
fun ZoomableImageWithButtonsAndTouch(
    imageModifier: Modifier = Modifier,
    maxScale: Float = 2.0f,
    minScale: Float = 1.0f
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var panx by remember { mutableStateOf(0f) }
    var pany by remember { mutableStateOf(0f) }

    val context = LocalContext.current
    val drawable = context.getDrawable(R.drawable.card_location)
    val bitmap = drawable?.toBitmap()

    val drawable_icon = context.getDrawable(R.drawable.map_pin)
    val bitmap_icon = drawable_icon?.toBitmap()




    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        val newScale = (scale * zoom).coerceIn(minScale, maxScale)

                        val maxOffsetX = (200 * (scale - 1)).coerceAtLeast(0f)
                        val maxOffsetY = (380 * (scale - 1)).coerceAtLeast(0f)


                        val newOffset = Offset(
                            x = (offset.x + pan.x * scale).coerceIn(
                                -maxOffsetX.dp.toPx(),
                                maxOffsetX.dp.toPx()
                            ),
                            y = (offset.y + pan.y * scale).coerceIn(
                                -maxOffsetY.dp.toPx(),
                                maxOffsetY.dp.toPx()
                            )
                        )

                        Log.d("vista de X", newOffset.x.toString())
                        Log.d("vista de Y", newOffset.y.toString())

                        scale = newScale
                        offset = newOffset
                        panx = pan.x
                        pany = pan.y
                    }
                }
        ){
            Image(
                painter = rememberImagePainter(data = "https://dfapruebaf.blob.core.windows.net/mapas/piso_1.png"),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            )

            Column(
                modifier = Modifier
                    .padding(end = 16.dp )
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                ZoomButton(
                    id = R.drawable.baseline_zoom_in_24
                ) {
                    val newScale = (scale * 1.2f).coerceAtMost(maxScale)
                    scale = newScale
                }
                Spacer(modifier = Modifier.padding(8.dp))

                ZoomButton(
                    id = R.drawable.baseline_zoom_out_24
                ) {
                    val newScale = (scale * 0.8f).coerceAtLeast(minScale)
                    scale = newScale
                    val maxOffsetX = (200 * (scale - 1)).coerceAtLeast(0f)
                    val maxOffsetY = (380 * (scale - 1)).coerceAtLeast(0f)
                    val newOffset = Offset(
                        x = ((offset.x + panx * scale * 10) * 4).coerceIn(-maxOffsetX, maxOffsetX),
                        y = ((offset.y + pany * scale) * 38).coerceIn(-maxOffsetY, maxOffsetY)
                    )
                    offset = newOffset
                }
            }

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
                    bitmap?.let { image ->
                        drawImage(image.asImageBitmap(), Offset(310f, 1650f))
                    }
                    bitmap_icon?.let { image ->
                        drawImage(image.asImageBitmap(), Offset(360f, 1660f))
                    }

                    val text = "Aula 102"
                    val textSize = 20f
                    val textColor = Color.White
                    val textPosition = Offset(340f, 1735f) // Posición del texto


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

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ){
                ElevatedButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 90.dp, end = 30.dp, start = 30.dp)
                        .fillMaxWidth()
                        .height(95.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent

                    ),contentPadding = PaddingValues(),
                    enabled = false,
                    shape = RoundedCornerShape(14.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        disabledElevation = 3.dp
                    )

                ) {
                    CardTeorica()
                }

            }

            /*val boxSize = 20.dp
            val boxPosition = Offset(
                x = 50 * scale - offset.x,
                y = 50 * scale + offset.y
            )

            Box(
                modifier = Modifier
                    .size(boxSize)
                    .offset(boxPosition.x.dp, boxPosition.y.dp)
                    .background(Color.Red)
            )*/




            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
                            ),
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 0.dp,
                            shape = CircleShape,
                            clip = true
                        )
                        .clickable{  },
                    contentAlignment = Alignment.BottomEnd
                ){
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "volver",
                        tint = Color.White
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 20.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF1D93BB), Color(0xFF4579CB), Color(0xFF6C5FDA))
                            ),
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = 0.dp,
                            shape = CircleShape,
                            clip = true
                        )
                        .clickable{  },
                    contentAlignment = Alignment.BottomEnd
                ){
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "volver",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ZoomButton(
    id: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFFC8DBF8), shape = CircleShape)
            .shadow(
                elevation = 0.dp,
                shape = CircleShape,
                clip = true
            )
            .clickable{ onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = id),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(25.dp)

        )
    }
}