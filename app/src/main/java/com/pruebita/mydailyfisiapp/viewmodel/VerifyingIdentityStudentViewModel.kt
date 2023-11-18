package com.pruebita.mydailyfisiapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pruebita.mydailyfisiapp.data.model.domain.User
import com.pruebita.mydailyfisiapp.data.model.helpers.UserManager
import com.pruebita.mydailyfisiapp.data.repository.repositories.CourseRepositoryImpl

import com.pruebita.mydailyfisiapp.data.repository.repositories.PythonAPIImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class VerifyingIdentityStudentViewModel @Inject constructor(private val context: Context) : ViewModel()  {
    private val userManager: UserManager = UserManager(context)
    private val repoCourse: CourseRepositoryImpl = CourseRepositoryImpl()

    private val _currentUser = MutableLiveData(userManager.getIdUser())
    val currentIdUser: MutableLiveData<Int> = _currentUser

    private val _idCourse= MutableLiveData<Int>(-1)
    val idCourse: LiveData<Int> = _idCourse

    private val _idSubPart= MutableLiveData<Int>(-1)
    val idSubPart: LiveData<Int> = _idSubPart

    fun getCourseDetails(): Pair<String, String> {
        val idCourse = _idCourse.value
        val idSubPart = _idSubPart.value
        return if(idCourse != null && idSubPart!= null){
            repoCourse.getCourseCardInfo(idCourse,idSubPart)
        }else{
            Pair("","")
        }
    }

    fun updateCourseRecognition(idCourse: Int, idSubPart: Int){
        _idCourse.value = idCourse
        _idSubPart.value = idSubPart
    }


    /*@RequiresApi(Build.VERSION_CODES.O)
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
                    println("Error en el envio de imformacion ${exception.message}")
                }
            }
        )
        return estadoRecognize
    }*/
}