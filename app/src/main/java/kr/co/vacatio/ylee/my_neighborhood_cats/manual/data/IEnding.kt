package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET

interface IEnding {
//    TODO(추후변경가능)
    @GET("/getEnding11")
    fun getEnding00() : Call<String>
}