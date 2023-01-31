package com.example.volleypostsave.network

interface ResponseHandler {
    fun onResponse(string: String)
    fun onError(string: String)
}