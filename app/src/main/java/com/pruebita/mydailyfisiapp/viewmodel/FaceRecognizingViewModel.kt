package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.PythonAPIImpl
import com.pruebita.mydailyfisiapp.ui.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaceRecognizingViewModel @Inject constructor(private val context: Context) : ViewModel() {
    private val userManager: UserManager = UserManager(context)
    private val StorageImage: StorageImagesImpl = StorageImagesImpl()
    private val PythonAPI: PythonAPIImpl = PythonAPIImpl()

    private val _currentUser = MutableLiveData(userManager.getUser() ?: User())
    val currentUser: LiveData<User> = _currentUser

    fun getMainRoute():String{
        var route = when (userManager.getIdRol()) {
            1 -> { AppScreens.MainStudentScreen.route }
            2 -> { AppScreens.MainDeleScreen.route }
            else -> { AppScreens.MainTeacherScreen.route }
        }
        return route
    }




    /*fun takePicture(
        camaraController: LifecycleCameraController,
        executor: Executor,
        nameImage: String,
        nameSubFolder: String,
        nameMainFolder: String
    ){
        val file = File.createTempFile(nameImage,".jpg")

        val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
        camaraController.takePicture(
            outputDirectory,
            executor,
            object: ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    println(outputFileResults.savedUri)
                    //outputFileResults.savedUri?.let {StorageImage.ImageToStorageFirebase(it,nameImage,nameSubFolder,nameMainFolder) }
                }
                override fun onError(exception: ImageCaptureException) {
                    println("error capturando${exception.localizedMessage}")
                }
            }
        )

    }*/

    fun sentImages(id_user: Int): Boolean? {
        val estadoisFace = PythonAPI.isface.value
        PythonAPI.sendPostImage(id_user)
        return estadoisFace
    }

    /*@androidx.annotation.OptIn(androidx.camera.view.video.ExperimentalVideo::class)
    fun recordVideo(
        context: Context,
        controller: LifecycleCameraController
    ) {
        if (recording != null) {
            recording?.stop()
            recording = null
        } else {
           // val directoryPath = "/data/app"
            //val outputFile = File(directoryPath, "my-recording.mp4")
            val outputFile = File.createTempFile("my-recording",".mp4")
            val outputOptions = FileOutputOptions.Builder(outputFile).build()
            println("aquuiiiiiiii" + outputFile.toString())

            if (
                ActivityCompat.checkSelfPermission
                    (context, android.Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED
                ) {
                return
            }

            // Iniciar la grabación
            recording = controller.startRecording(
                outputOptions,
                AudioConfig.create(true),
                ContextCompat.getMainExecutor(context)
            ) { event ->
                when (event) {

                    is VideoRecordEvent.Finalize -> {
                        if (event.hasError()) {
                            recording?.close()
                            recording = null
                            println("nuuuuuuuuuuuuu")
                        }
                        else{
                           println("Competado uuuuuuuuu")
                        }
                    }
                    is VideoRecordEvent.Start -> {
                        println("Empiezaaaaaaaa")
                    }
                }
            }

            // Detener la grabación después de 4 segundos (4000 milisegundos)
            android.os.Handler(Looper.getMainLooper()).postDelayed({
                if (recording != null) {
                    recording?.stop()
                    recording = null
                }
            }, 4000) // 4 segundos
        }
    }*/



}


