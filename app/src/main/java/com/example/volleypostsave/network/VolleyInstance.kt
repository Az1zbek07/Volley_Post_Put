package com.example.volleypostsave.network

import com.android.volley.toolbox.StringRequest
import com.example.volleypostsave.MyApp
import com.example.volleypostsave.model.Data
import com.example.volleypostsave.util.Constants

class VolleyInstance {
    fun get(responseHandler: ResponseHandler){
        val getRequest = object : StringRequest(
            Method.GET,
            Constants.BASE_URL,
            {
            responseHandler.onResponse(it)
            },
            {
                responseHandler.onError(it.stackTraceToString())
            }
        ) {}
        MyApp.myApp?.addToRequest(getRequest)
    }

    fun getById(id: Int, responseHandler: ResponseHandler){
        val getRequest = object : StringRequest(
            Method.GET,
            "${Constants.BASE_URL}/$id",
            {
            responseHandler.onResponse(it)
            },
            {
                responseHandler.onError(it.stackTraceToString())
            }
        ) {}
        MyApp.myApp?.addToRequest(getRequest)
    }

    fun post(body: Data, responseHandler: ResponseHandler){
        val postRequest = object : StringRequest(
            Method.POST,
            Constants.BASE_URL,
            { responseHandler.onResponse(it) },
            { responseHandler.onError(it.toString()) }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val hashMap = HashMap<String, String>()
                hashMap["first_name"] = body.first_name
                hashMap["last_name"] = body.last_name
                hashMap["email"] = body.email
                return hashMap
            }
        }
        MyApp.myApp?.addToRequest(postRequest)
    }

    fun put(body: Data, responseHandler: ResponseHandler){
        val putRequest = object : StringRequest(
            Method.PUT,
            "${Constants.BASE_URL}/${body.id}",
            { responseHandler.onResponse(it) },
            { responseHandler.onError(it.toString()) }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val hashMap = HashMap<String, String>()
                hashMap["id"] = body.id.toString()
                hashMap["first_name"] = body.first_name
                hashMap["last_name"] = body.last_name
                hashMap["email"] = body.email
                return hashMap
            }
        }
        MyApp.myApp?.addToRequest(putRequest)
    }

    fun delete(id: Int, responseHandler: ResponseHandler){
        val deleteRequest = object : StringRequest(
            Method.DELETE,
            "${Constants.BASE_URL}/$id",
            { responseHandler.onResponse(it) },
            { responseHandler.onError(it.toString()) }
        ) {}

        MyApp.myApp?.addToRequest(deleteRequest)
    }
}