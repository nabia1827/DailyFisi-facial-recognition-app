package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import com.pruebita.mydailyfisiapp.data.model.UserManager
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.util.concurrent.Executor
import javax.inject.Inject

@HiltViewModel
class RecognizingViewModel @Inject constructor(private val context: Context) : ViewModel() {
    private val userManager: UserManager = UserManager(context)
    private val StorageImage: StorageImagesImpl = StorageImagesImpl()

    fun getMainRoute():String{
        var route = when (userManager.getIdRol()) {
            1 -> { AppScreens.MainStudentScreen.route }
            2 -> { AppScreens.MainDeleScreen.route }
            else -> { AppScreens.MainTeacherScreen.route }
        }
        return route
    }
    //Facial_Identity
    //user_id
    //photo_1

    //Profile
    //

    fun takePicture(
        camaraController: LifecycleCameraController,
        executor: Executor,
        nameImage: String,
        nameFile: String
    ){
        val file = File.createTempFile(nameImage,".jpg")
        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        camaraController.takePicture(
            outputDirectory,
            executor,
            object: ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    println(outputFileResults.savedUri)
                    outputFileResults.savedUri?.let {StorageImage.uploadImageToStorage(it,nameFile,"nuevo","a") }
                }
                override fun onError(exception: ImageCaptureException) {
                    println("error")
                }
            }
        )
    }
}