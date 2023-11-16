package com.pruebita.mydailyfisiapp.data.model.helpers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class ConvertYuvtoJPEG(
    private val image: ImageProxy
) {

    fun convertYuvToBitmap(): Bitmap {
        val yBuffer: ByteBuffer = image.planes[0].buffer
        val uBuffer: ByteBuffer = image.planes[1].buffer
        val vBuffer: ByteBuffer = image.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)
        yBuffer.get(nv21, 0, ySize)
        uBuffer.get(nv21, ySize, uSize)
        vBuffer.get(nv21, ySize + uSize, vSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        val outputStream = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, yuvImage.width, yuvImage.height), 100, outputStream)

        val jpegBytes = outputStream.toByteArray()
        val rotatedBitmap = rotateBitmap(BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size), image.imageInfo.rotationDegrees)
        return rotatedBitmap
    }


    private fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(rotationDegrees.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

}