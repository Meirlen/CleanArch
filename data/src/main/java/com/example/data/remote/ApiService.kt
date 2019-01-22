package com.example.data.remote

import com.example.gateway.entity.Board
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("1/members/me/boards")
    fun getBoards(): Call<List<Board>>
}