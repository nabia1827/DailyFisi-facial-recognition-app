package com.pruebita.mydailyfisiapp.ui.screens.facialrecognizer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.pruebita.mydailyfisiapp.data.model.helpers.FaceRecognitionAnalyzer
import com.pruebita.mydailyfisiapp.data.model.helpers.FaceRegisterAnalyzer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Camera(
    lifecycle: LifecycleOwner = LocalLifecycleOwner.current,
    countFace: MutableState<Int> = remember { mutableStateOf(0) } ,
    isDetecting: Boolean = false
) {
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {

    }) {
        CameraPreview(
            lifecycle,
            modifier = Modifier.padding(it),
            countFace,
            isDetecting
        )
    }
}

@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
fun CameraPreview(
    lifecycle: LifecycleOwner,
    modifier: Modifier = Modifier,
    countFace: MutableState<Int>,
    isDetecting: Boolean
) {
    val contextx = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(contextx)
    }

    fun onCountUpdated(updatedCount: Int){
        countFace.value = updatedCount
    }

    AndroidView(
        factory = { context->
            PreviewView(context).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                setBackgroundColor(Color.RED)
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }.also{ previewView ->
                startFaceRecognition(
                    context = context,
                    cameraController = cameraController,
                    lifecycleOwner = lifecycle,
                    previewView = previewView,
                    onCount = ::onCountUpdated,
                    isDetecting = isDetecting
                )
                cameraController.bindToLifecycle(lifecycle)
                previewView.controller = cameraController
            }
        }
    )
}

fun startFaceRecognition(
    context: Context,
    cameraController: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    onCount: (Int) -> Unit,
    isDetecting: Boolean
) {
    cameraController.imageAnalysisTargetSize = CameraController.OutputSize(AspectRatio.RATIO_16_9)
    cameraController.setImageAnalysisAnalyzer(
        ContextCompat.getMainExecutor(context),
        if(!isDetecting){
            FaceRegisterAnalyzer(previewView, onCount)
        } else {
            FaceRecognitionAnalyzer(previewView, onCount, id_user = 3, id_curso = 5)
        }
    )
    cameraController.bindToLifecycle(lifecycleOwner)
    previewView.controller = cameraController
}

