package com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer

import android.content.Context
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pruebita.mydailyfisiapp.viewmodel.RecognizingViewModel
import com.pruebita.mydailyfisiapp.viewmodel.VerifyingIdentityStudentViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
@androidx.annotation.OptIn(androidx.camera.view.video.ExperimentalVideo::class)
fun Camera(
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner,
    /*context: Context = LocalContext.current,*/
) {
    /*val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current
    val executor = ContextCompat.getMainExecutor(context)

    val verifyingIdentityStudentViewModel: VerifyingIdentityStudentViewModel = hiltViewModel()
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }*/
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        /*FloatingActionButton(
            onClick = {
                println("Llamaanddoooooooo APIIIIIIIIII")
                verifyingIdentityStudentViewModel.takePictureToAPI(
                    cameraController,
                    executor,
                    "phtoPrueba"
                )

            }
        ) {
            Text(text = "Holitass")
        }*/

    }) {
        CamaraComposable(cameraController, lifecycle, modifier = Modifier.padding(it))
    }
}


@Composable
fun CamaraComposable(
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner,
    modifier: Modifier = Modifier,
) {
    cameraController.bindToLifecycle(lifecycle)
    AndroidView(modifier = modifier, factory = { context ->
        val previewView = PreviewView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
        }
        previewView.controller = cameraController
        previewView
    })
}

