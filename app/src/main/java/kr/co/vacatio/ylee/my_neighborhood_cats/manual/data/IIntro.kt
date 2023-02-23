package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET

interface IIntro {
    @GET("/text/GameIntro")
    fun getGameIntro() : Call<TextResponse>

    @GET("/text/getProlog")
    fun getProlog() : Call<TextResponse>
}