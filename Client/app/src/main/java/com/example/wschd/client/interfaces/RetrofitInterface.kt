package com.jjosft.android.lottovillage.interfaces

import com.jjosft.android.lottovillage.model.Model
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by JJSOFT-DESKTOP on 2017-08-17.
 */
interface RetrofitInterface {

    @POST("login")
    fun postLogin(@Body paras: RequestBody): Observable<Model.DefaultResponse>

    @POST("register")
    fun postRegister(@Body params: RequestBody): Observable<Model.DefaultResponse>

    @GET("my_point")
    fun getMyPoint(): Observable<Model.SingleIntResponse>

    @GET("lotto_rounds")
    fun getLottoRounds(): Observable<Model.SingleIntResponse>

    @GET("details_of_lotto")
    fun getDetailsOfLotto(@Query("rounds") rounds : String): Observable<Model.LottoResponse>

    @GET("details_of_participation")
    fun getDetailsOfParticipation(@Query("event_type") eventType : String,
                                  @Query("event_date") eventDate : String,
                                  @Query("event_number") eventNumber : String,
                                  @Query("confirm_status") isConfirmStatus : Boolean): Observable<Model.ParticipationResponse>

    @GET("details_of_all_participation")
    fun getDetailsOfAllParticipation(): Observable<Model.ParticipationResponse>

    @GET("details_of_winning_info")
    fun getDetailsOfWinningInfo(@Query("event_type") eventType : String,
                                @Query("event_date") eventDate : String,
                                @Query("event_number") eventNumber : String): Observable<Model.WinningInfoResponse>

    @GET("details_of_all_winning_info")
    fun getDetailsOfAllWinningInfo(): Observable<Model.WinningInfoResponse>

    @POST("participation")
    fun postParticipation(@Body params: RequestBody): Observable<Model.DefaultResponse>

    @GET("details_of_all_product_info")
    fun getDetailsOfAllProductInfo(@Query(""))
}