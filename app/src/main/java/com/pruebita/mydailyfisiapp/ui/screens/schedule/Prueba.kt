package com.pruebita.mydailyfisiapp.ui.screens.schedule

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer.Camera


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Prueba(){
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current
    Column (modifier = Modifier.fillMaxSize()){
        Camera(cameraController = cameraController, lifecycle = lifecycle/*, context*/)
    }
}
