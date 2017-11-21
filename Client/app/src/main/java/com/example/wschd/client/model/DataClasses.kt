package com.example.wschd.client.model

import com.google.gson.annotations.SerializedName


object Model {
    data class DefaultResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String)

    data class ParticipationResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfParticipation: ArrayList<DetailsOfParticipation>)
    data class DetailsOfParticipation(@SerializedName("EVENT_TYPE") val eventType: String, @SerializedName("WINNING_NUMBER_1") val winningNumber1: Int, @SerializedName("WINNING_NUMBER_2") val winningNumber2: Int,
                                      @SerializedName("WINNING_NUMBER_3") val winningNumber3: Int, @SerializedName("WINNING_NUMBER_4") val winningNumber4: Int,
                                      @SerializedName("WINNING_NUMBER_5") val winningNumber5: Int, @SerializedName("WINNING_NUMBER_6") val winningNumber6: Int,
                                      @SerializedName("PARTICIPATING_TIME") val participatingTime: String)

    data class ParticipationHistoryResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfParticipationHistory: ArrayList<DetailsOfParticipationHistory>)
    data class DetailsOfParticipationHistory(@SerializedName("EVENT_TYPE") val eventType: String, @SerializedName("EVENT_DATE_HOUR") val eventDateHour: String, @SerializedName("WINNING_RATE") val winningRate: String,
                                             @SerializedName("WINNING_NUMBER_1") val winningNumber1: Int, @SerializedName("WINNING_NUMBER_2") val winningNumber2: Int, @SerializedName("WINNING_NUMBER_3") val winningNumber3: Int,
                                             @SerializedName("WINNING_NUMBER_4") val winningNumber4: Int, @SerializedName("WINNING_NUMBER_5") val winningNumber5: Int, @SerializedName("WINNING_NUMBER_6") val winningNumber6: Int,
                                             @SerializedName("PRIZE") val prize: Int, @SerializedName("PARTICIPATING_TIME") val participatingTime: String, @SerializedName("WINNING_TIME") val winningTime: String)

    data class PointHistoryResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val detailsOfPointHistory: ArrayList<DetailsOfPointHistory>)
    data class DetailsOfPointHistory(@SerializedName("SAVING_TIME") val savingTime: String, @SerializedName("SCORE_TYPE") val scoreType: Int, @SerializedName("SCORE") val score: Int)

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
}