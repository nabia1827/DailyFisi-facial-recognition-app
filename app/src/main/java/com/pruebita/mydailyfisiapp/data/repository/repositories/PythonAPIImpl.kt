package com.pruebita.mydailyfisiapp.data.repository.repositories

import com.pruebita.mydailyfisiapp.data.model.RecognizeResponse
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
    private val serverUrl = "http://192.168.1.4:5000/upload_images"
    private val client = OkHttpClient()

    fun sendPostImage(id_user: String) {
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("id_user", id_user)
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
    }

    fun getStateValidation(id_user: Int, id_image: String): Boolean {
        val client = OkHttpClient()
        var estado = false
        val url = "http://192.168.1.4:5000/api?id_user=$id_user&id_image=$id_image"
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
                        estado = apiResponse.estado
                        println("API PYTHON CON EXITO: ${apiResponse.estado}")
                    }
                }
            }
        })
        return estado
    }

}