package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IEnding {
    @GET("/text/getEnding/{id}")
    fun getEnding(
        @Path("id") id : Int
    ) : Call<TextResponse>
}