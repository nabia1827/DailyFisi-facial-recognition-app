package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.repository.repositories.PythonAPIImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.datetime.*
import java.io.File
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel

class VerifyingIdentityStudentViewModel @Inject constructor(private val context: Context) : ViewModel()  {


    private val PythonAPI: PythonAPIImpl = PythonAPIImpl()
    private val StorageImage: StorageImagesImpl = StorageImagesImpl()
    @RequiresApi(Build.VERSION_CODES.O)
    fun takePictureToAPI(
        camaraController: LifecycleCameraController,
        executor: Executor,
        nameImage: String,
        id_user: Int = 2,
        id_curso: Int = 5
    ): Boolean? {
        val file = File.createTempFile(nameImage,".jpg")


        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        val estadoRecognize = PythonAPI.isRecognized.value

        val fechaActual = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val fechaFormateada = fechaActual.toString()
        val fechaModificada = fechaFormateada.replace('-', '_')
        camaraController.takePicture(
            outputDirectory,
            executor,
            object: ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    outputFileResults.savedUri?.let {
                        val id_image = "${id_user}_${fechaModificada}_${id_curso}"

                        StorageImage.ImageToStorageFirebase(it, nameImage=id_image, namePrincipalFolder = "temp")
                        PythonAPI.getStateValidation(id_user,id_image)
                    }
                }
                override fun onError(exception: ImageCaptureException) {
                    println("Error en el envio de imformacion ${exception.imageCaptureError}")
                }
            }
        )
        return estadoRecognize
    }
}