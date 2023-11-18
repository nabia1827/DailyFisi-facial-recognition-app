package com.pruebita.mydailyfisiapp.data.repository.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pruebita.mydailyfisiapp.data.model.domain.RecognizeResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class PythonAPIImpl {
    private val serverUrl = "http://192.168.1.67:5000/upload_images"
    private val client = OkHttpClient()

    private val _isRecognized = MutableLiveData<Boolean>(false)
    val isRecognized: LiveData<Boolean> = _isRecognized

    private val _isface = MutableLiveData<Boolean>(false)
    val isface: LiveData<Boolean> = _isface

    fun sendPostImage(id_user: Int) {

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id_user", id_user.toString())
            .build()

        val request = Request.Builder()
            .url(serverUrl)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("API PYTHON Error: "+ e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    println("API PYTHON CON EXITO")
                }
            }
        })

        /*val client = OkHttpClient()
        val url = "http://192.168.1.4:5000/upload_images?id_user=$id_user"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("API PYTHON Error: "+ e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    val apiResponse = responseData?.let {
                        Json.decodeFromString<RecognizeResponse>(
                            it
                        )
                    }
                    if (apiResponse != null) {
                        _isface.postValue(apiResponse.estado)
                        println("API PYTHON CON EXITO RECOG: ${apiResponse.estado}")
                    }
                }
            }
        })*/


    }

    fun getStateValidation(id_user: Int, id_image: String){
        val client = OkHttpClient()
        val url = "http://192.168.1.67:5000/api?id_user=$id_user&id_image=$id_image"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("API PYTHON Error: "+ e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    val apiResponse = responseData?.let {
                        Json.decodeFromString<RecognizeResponse>(
                            it
                        )
                    }
                    if (apiResponse != null) {
                        _isRecognized.postValue(apiResponse.estado)
                        println("API PYTHON CON EXITO: ${apiResponse.estado}")
                    }
                }
            }
        })
    }

}