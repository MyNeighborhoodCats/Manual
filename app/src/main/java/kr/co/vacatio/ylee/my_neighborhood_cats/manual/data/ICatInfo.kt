package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET

interface ICatInfo {
//    TODO(추후변경가능)
    @GET("/getCat1")
    fun getCat1() : Call<CatDto>
}