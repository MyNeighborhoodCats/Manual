package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import com.google.gson.annotations.SerializedName

data class CatDto(
    @SerializedName("character1")
    val character1 : String,
    @SerializedName("character2")
    val character2 : String,
    @SerializedName("character3")
    val character3 : String,
    @SerializedName("info")
    val info : String
)
