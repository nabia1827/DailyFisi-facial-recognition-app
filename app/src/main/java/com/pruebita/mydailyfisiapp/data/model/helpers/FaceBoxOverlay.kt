package com.pruebita.mydailyfisiapp.data.model.helpers

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.ContentInfo
import android.view.View
import androidx.camera.view.PreviewView
import androidx.core.graphics.toRect
import androidx.lifecycle.MutableLiveData
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

class FaceBoxOverlay () {

    fun getBoxRect(imageRectWidth: Float, imageRectHeight: Float, faceBoundinBox: Rect, preview: PreviewView): Rect {
        val scaleX = preview.width.toFloat() / (imageRectWidth)
        val scaleY = preview.height.toFloat() / (imageRectHeight)

        // Aplica la escala al rectángulo del rostro
        val scaledRect = Rect(
            (faceBoundinBox.left * (scaleX-0.2)).toInt(),
            (faceBoundinBox.top * (scaleY-0.6)).toInt(),
            (faceBoundinBox.right * (scaleX-0.2)).toInt(),
            (faceBoundinBox.bottom * (scaleY-0.25)).toInt()
        )

        // Asegúrate de que el rectángulo no se salga de los límites de la vista previa
        val maxWidth = preview.width
        val maxHeight = preview.height

        scaledRect.left = max(0, min(scaledRect.left, maxWidth))
        scaledRect.top = max(0, min(scaledRect.top, maxHeight))
        scaledRect.right = max(0, min(scaledRect.right, maxWidth))
        scaledRect.bottom = max(0, min(scaledRect.bottom, maxHeight))

        return scaledRect
    }
    /*fun clear(){
        synchronized(lock){
            faceboxes.clear()
        }
        postInvalidate()
    }

    fun add(faceBox: FaceBox){
        synchronized(lock){
            faceboxes.add(faceBox)
        }
    }*/
}