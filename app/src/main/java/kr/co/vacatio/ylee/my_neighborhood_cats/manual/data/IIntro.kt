package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET

interface IIntro {
    @GET("/GameIntro")
    fun getGameIntro() : Call<String>

    @GET("/getProlog")
    fun getProlog() : Call<String>
}