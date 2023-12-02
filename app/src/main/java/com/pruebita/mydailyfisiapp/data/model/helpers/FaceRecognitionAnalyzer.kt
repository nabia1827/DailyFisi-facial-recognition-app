package com.pruebita.mydailyfisiapp.data.model.helpers

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.media.Image
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.view.PreviewView
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.pruebita.mydailyfisiapp.data.repository.repositories.PythonAPIImpl
import com.pruebita.mydailyfisiapp.data.repository.repositories.StorageImagesImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.io.ByteArrayOutputStream
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FaceRecognitionAnalyzer(
    private val previewView: PreviewView,
    private val onCount: (Int) -> Unit,
    private val idUser: Int,
    private val idCourse: Int
) : ImageAnalysis.Analyzer
{
    private val StorageImage: StorageImagesImpl = StorageImagesImpl()
    private val pythonAPI: PythonAPIImpl = PythonAPIImpl()

    private var isRecognized: Boolean = false
    
    companion object{
        const val THROTTLE_TIMEOUT_MS = 1_000L
    }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val faceDetector: FaceDetector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .build()
    )

    private fun Verifiyng(data: ByteArray) {
        if(!isRecognized){
            val fechaActual = Clock.System.todayIn(TimeZone.currentSystemDefault())
            val fechaActualFormateada = fechaActual.toString()
            val fechaActualModificada = fechaActualFormateada.replace('-', '_')
            val id_image = "${idUser}_${fechaActualModificada}_${idCourse}"

            StorageImage.ImageToStorageFirebase(byte = data,filpath = "temp",nameImage=id_image)

            pythonAPI.getStateValidation(idUser,id_image)
            if(pythonAPI.isRecognized.value == true){
                isRecognized = true
                onCount(1)
            }else{
                isRecognized = true
                onCount(1)
            }
        }
    }

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override fun   analyze(image: ImageProxy) {
        scope.launch{

            val mediaImage: Image = image.image?: run {image.close(); return@launch}
            val inputImage: InputImage = InputImage.fromMediaImage(mediaImage, image.imageInfo.rotationDegrees)
            suspendCoroutine {continuation ->
                faceDetector.process(inputImage)
                    .addOnSuccessListener { faces ->
                        previewView.overlay.clear()
                        faces.forEach { face ->

                            val paint = Paint().apply {
                                color = Color.RED
                                style = Paint.Style.STROKE
                                strokeWidth = 6.0f
                            }

                            val m = FaceBoxOverlay()
                            val rectShape = ShapeDrawable(RectShape())
                            rectShape.paint.set(paint)
                            rectShape.bounds = m.getBoxRect(image.cropRect.width().toFloat(), image.cropRect.height().toFloat(), face.boundingBox, previewView)

                            val Converter = ConvertYuvtoJPEG(image)
                            val bitmap = Converter.convertYuvToBitmap()
                            val byteArrayOutputStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                            val data = byteArrayOutputStream.toByteArray()

                            Verifiyng(data)
                            previewView.overlay.add(rectShape)

                        }

                    }
                    .addOnFailureListener{
                        println("Unexpected error in FaceRecognitionAnalyzer: "+ it.message)
                    }
                    .addOnCompleteListener{
                        continuation.resume(Unit)
                    }
            }
            delay(THROTTLE_TIMEOUT_MS)
        }.invokeOnCompletion {exception ->
            exception?.printStackTrace()
            image.close()
        }

    }
}
