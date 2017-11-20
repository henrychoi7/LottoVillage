package com.example.wschd.client.interfaces

import com.example.wschd.client.model.Model
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RetrofitInterface {

    @POST("login")
    fun postLogin(@Body paras: RequestBody): Observable<Model.DefaultResponse>

    @POST("register")
    fun postRegister(@Body params: RequestBody): Observable<Model.DefaultResponse>

    @GET("my_point")
    fun getMyPoint(): Observable<Model.SingleIntResponse>
}