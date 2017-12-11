package com.jjosft.android.lottovillage.model

import com.google.gson.annotations.SerializedName

/**
 * Created by JJSOFT-DESKTOP on 2017-08-17.
 */

object Model {
    data class DefaultResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String)

    data class UserInfoResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("name") val name: String)

    data class ParticipationResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfParticipation: ArrayList<DetailsOfParticipation>)
    data class DetailsOfParticipation(@SerializedName("EVENT_TYPE") val eventType: String, @SerializedName("WINNING_NUMBER_1") val winningNumber1: Int, @SerializedName("WINNING_NUMBER_2") val winningNumber2: Int,
                                      @SerializedName("WINNING_NUMBER_3") val winningNumber3: Int, @SerializedName("WINNING_NUMBER_4") val winningNumber4: Int,
                                      @SerializedName("WINNING_NUMBER_5") val winningNumber5: Int, @SerializedName("WINNING_NUMBER_6") val winningNumber6: Int,
                                      @SerializedName("PARTICIPATING_TIME") val participatingTime: String)

    data class LottoResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfLotto: ArrayList<DetailsOfLotto>)
    data class DetailsOfLotto(@SerializedName("WINNING_DATE") val winningDate: String, @SerializedName("WINNING_NUMBER_1") val winningNumber1: Int,
                              @SerializedName("WINNING_NUMBER_2") val winningNumber2: Int, @SerializedName("WINNING_NUMBER_3") val winningNumber3: Int,
                              @SerializedName("WINNING_NUMBER_4") val winningNumber4: Int, @SerializedName("WINNING_NUMBER_5") val winningNumber5: Int,
                              @SerializedName("WINNING_NUMBER_6") val winningNumber6: Int, @SerializedName("BONUS_NUMBER") val bonusNumber: Int,
                              @SerializedName("TOTAL_PRIZE_1") val totalPrize1: String, @SerializedName("TOTAL_NUMBER_1") val totalNumber1: String, @SerializedName("PER_PRIZE_1") val perPrize1: String,
                              @SerializedName("TOTAL_PRIZE_2") val totalPrize2: String, @SerializedName("TOTAL_NUMBER_2") val totalNumber2: String, @SerializedName("PER_PRIZE_2") val perPrize2: String,
                              @SerializedName("TOTAL_PRIZE_3") val totalPrize3: String, @SerializedName("TOTAL_NUMBER_3") val totalNumber3: String, @SerializedName("PER_PRIZE_3") val perPrize3: String,
                              @SerializedName("TOTAL_PRIZE_4") val totalPrize4: String, @SerializedName("TOTAL_NUMBER_4") val totalNumber4: String, @SerializedName("PER_PRIZE_4") val perPrize4: String,
                              @SerializedName("TOTAL_PRIZE_5") val totalPrize5: String, @SerializedName("TOTAL_NUMBER_5") val totalNumber5: String, @SerializedName("PER_PRIZE_5") val perPrize5: String)

    data class WinningInfoResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfWinningInfo: ArrayList<DetailsOfWinningInfo>)
    data class DetailsOfWinningInfo(@SerializedName("EVENT_TYPE") val eventType: String, @SerializedName("EVENT_DATE_HOUR") val eventDateHour: String, @SerializedName("WINNING_NUMBER_1") val winningNumber1: Int,
                                    @SerializedName("WINNING_NUMBER_2") val winningNumber2: Int, @SerializedName("WINNING_NUMBER_3") val winningNumber3: Int, @SerializedName("WINNING_NUMBER_4") val winningNumber4: Int,
                                    @SerializedName("WINNING_NUMBER_5") val winningNumber5: Int, @SerializedName("WINNING_NUMBER_6") val winningNumber6: Int, @SerializedName("BONUS_NUMBER") val bonusNumber: Int)

    data class SingleIntResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val results: Int)
    //추가한 부분 11/27
    data class AllProductResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfAllProduct: ArrayList<DetailsOfAllProduct>)

    data class DetailsOfAllProduct(@SerializedName("DATE_TIME") val dateTime: String, @SerializedName("CONTENTS") val contents: String, @SerializedName("POINT") val point: String)

    data class PointHistoryResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfPointHistory: ArrayList<DetailsOfPointHistory>)
    data class DetailsOfPointHistory(@SerializedName("DATE_TIME") val dateTime: String, @SerializedName("CONTENTS") val contents: String, @SerializedName("POINT") val point: String)

    data class ParticipationHistoryResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfAllParticipation: ArrayList<DetailsOfParticipationHistory>)
    data class DetailsOfParticipationHistory(@SerializedName("DATE_TIME") val dateTime: String, @SerializedName("CONTENTS") val contents: String, @SerializedName("POINT") val point: String)

    data class PredictionResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfPrediction: DetailsOfPrediction)
    data class DetailsOfPrediction(@SerializedName("requestPredictionNumber1") val predictionNumber1: Int, @SerializedName("requestPredictionNumber2") val predictionNumber2: Int, @SerializedName("requestPredictionNumber3") val predictionNumber3: Int,
                                   @SerializedName("requestPredictionNumber4") val predictionNumber4: Int, @SerializedName("requestPredictionNumber5") val predictionNumber5: Int, @SerializedName("requestPredictionNumber6") val predictionNumber6: Int, @SerializedName("requestBonusNumber") val bonusNumber: Int)

    /**
     * 상점 물품 조회 데이터 클래스
     */
    data class ProductListResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("results") val detailsOfProductList: ArrayList<DetailsOfProductList>)

    data class DetailsOfProductList(@SerializedName("PRODUCT_CODE") val productCode: Int,
                                    @SerializedName("PRODUCT_NAME") val productName: String,
                                    @SerializedName("PRODUCT_PRICE") val productPrice: Int,
                                    @SerializedName("PRODUCT_STATUS") val productStatus: String,
                                    @SerializedName("PRODUCT_CONTENTS") val productContents: String)
}