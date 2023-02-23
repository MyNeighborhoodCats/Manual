package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ICatInfo {
    @GET("/text/getCat/{id}")
    fun getCat(
        @Path("id") id : Int
    ) : Call<CatDto>
}