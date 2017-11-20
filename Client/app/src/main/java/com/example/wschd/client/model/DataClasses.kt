package com.example.wschd.client.model

import com.google.gson.annotations.SerializedName


object Model {
    data class DefaultResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String)

    data class SingleIntResponse(@SerializedName("isSuccess") val isSuccess: Boolean, @SerializedName("errorMessage") val errorMessage: String, @SerializedName("results") val results: Int)
}