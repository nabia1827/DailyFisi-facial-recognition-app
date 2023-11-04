package com.pruebita.mydailyfisiapp.data.repository.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class StorageImagesImpl{
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    fun uploadImageToStorage(imageUri: Uri, nameImage:String,  nameSubFolder: String, namePrincipalFolder: String): String {
        val path = "$namePrincipalFolder/$nameSubFolder/$nameImage"
        val imagesRef: StorageReference = storageReference.child(path)
        val uploadTask =  imagesRef.putFile(imageUri)
        var imageUrl = ""
        uploadTask.addOnSuccessListener { taskSnapshot ->
            imagesRef.downloadUrl.addOnSuccessListener { downloadUri ->
                imageUrl = downloadUri.toString()
            }.addOnFailureListener { exception ->
                println("Fallo en la obtencion del Url")
            }
        }.addOnFailureListener { exception ->
            println("No reconoce el Uri")
        }
        return imageUrl
    }
}