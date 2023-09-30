package com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer

import android.view.ViewGroup
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera() {

    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val lifecycle = LocalLifecycleOwner.current


    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {

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