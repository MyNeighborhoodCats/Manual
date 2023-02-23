package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("character1")
    val character1 : String,
    @SerializedName("character2")
    val character2 : String,
    @SerializedName("character3")
    val character3 : String,
    @SerializedName("value1")
    val value1 : Float,
    @SerializedName("value2")
    val value2 : Float,
    @SerializedName("value3")
    val value3 : Float,
    @SerializedName("info")
    val info : String
)
