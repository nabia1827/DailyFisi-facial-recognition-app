package com.pruebita.mydailyfisiapp.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class StorageImages: ViewModel()
{
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    fun uploadImageToStorage(imageUri: Uri): UploadTask {
        val imagesRef: StorageReference = storageReference.child("images/${imageUri.lastPathSegment}")
        return imagesRef.putFile(imageUri)
    }
}