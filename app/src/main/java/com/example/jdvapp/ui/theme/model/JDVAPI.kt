package com.example.jdvapp.ui.theme.model

import androidx.compose.ui.text.input.TextFieldValue
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object JDVAPI {

    val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()
    val gson = Gson()
    val client = OkHttpClient()
    const val URL_SERVER = "http://82.216.137.249:8080/JDVBack"
    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use { //it:Response
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
    fun sendPost(url: String, toSend: Any?): String {
        println("url : $url")
        val json = gson.toJson(toSend)
        val body = json.toRequestBody(MEDIA_TYPE_JSON)
        val request = Request.Builder().url(url).post(body).build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            it.body.string()
        }
    }
    fun createUser(userPseudo: String) {
         sendPost("$URL_SERVER/createUser", userPseudo)
    }
    fun loadUser(userPseudo: String): UserBean {
        val responseJson = sendPost("$URL_SERVER/loadUser", userPseudo)
        return gson.fromJson(responseJson, UserBean::class.java)
    }
    fun addGrid(userId:Long,grid:String){
         sendPost("$URL_SERVER/addGrid?userId=$userId", grid)
    }
    fun deleteGrid(userId:Long,gridNumber:Int){
        sendPost("$URL_SERVER/deleteGrid?userId=$userId", gridNumber)
    }
}

fun main() {
    //JDVAPI.createUser("test front2")
    val test1=JDVAPI.loadUser("test front")
   // println(test1)
   // val testGrid=JDVAlgo.randomGrid().toString()
   // JDVAPI.addGrid(11,testGrid)
   // println(test1)
    JDVAPI.deleteGrid(11,2)
    println(test1)
}