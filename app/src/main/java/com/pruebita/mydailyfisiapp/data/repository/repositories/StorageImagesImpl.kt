package com.pruebita.mydailyfisiapp.data.repository.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class StorageImagesImpl{
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    fun uploadImageToStorage(imageUri: Uri, nameImage:String,  nameSubFolder: String, namePrincipalFolder: String): UploadTask {
        val path = "$namePrincipalFolder/$nameSubFolder/$nameImage"
        val imagesRef: StorageReference = storageReference.child(path)
        return imagesRef.putFile(imageUri)
    }
}