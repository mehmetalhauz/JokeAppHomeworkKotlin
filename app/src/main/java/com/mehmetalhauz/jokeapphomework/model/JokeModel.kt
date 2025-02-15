package com.mehmetalhauz.jokeapphomework.model

import com.google.gson.annotations.SerializedName

// Sealed class ile farklı şaka türlerini yönetiyoruz
sealed class JokeModel{
    data class SingleJoke(val joke: String) : JokeModel()
    data class TwoPartJoke(val setup: String, val delivery: String) : JokeModel()
    object Unknown : JokeModel()
}

// API'dan gelen JSON verisini temsil eden model
data class JokeResponse(
    @SerializedName("error") val error: Boolean,
    @SerializedName("category") val category: String,
    @SerializedName("type") val type : String,
    @SerializedName("flags") val flags: Flags,
    @SerializedName("id") val id: Int,
    @SerializedName("safe") val safe: Boolean,
    @SerializedName("lang") val lang: String,
    @SerializedName("joke") val joke: String?, // Single türü için
    @SerializedName("setup") val setup: String?, // TwoPart için
    @SerializedName("delivery") val delivery: String? // TwoPart için
){
    // Gelen JSON verisini JokeModel formatına dönüştüren fonksiyon
    fun toJokeModel(): JokeModel {
        return when(type){
            "single" -> joke?.let { JokeModel.SingleJoke(it) } ?: JokeModel.Unknown
            "twopart" -> if(setup != null && delivery != null){
                JokeModel.TwoPartJoke(setup, delivery)
            } else {
                JokeModel.Unknown
            }
            else -> JokeModel.Unknown
        }
    }
}

// JSON içindeki "flags" nesnesini temsil eden veri modeli
data class Flags(
    @SerializedName("nsfw") val nsfw: Boolean,
    @SerializedName("religious") val religious: Boolean,
    @SerializedName("political") val political: Boolean,
    @SerializedName("racist") val racist: Boolean,
    @SerializedName("sexist") val sexist: Boolean,
    @SerializedName("explicit") val explicit: Boolean
)
