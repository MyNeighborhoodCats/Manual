package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import com.google.gson.annotations.SerializedName

data class TextResponse (
    @SerializedName("text")
    val result : String
)