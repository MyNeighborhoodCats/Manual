package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

class CatInfoRepository {

    val getCatInfoService = RetrofitObject.getCatInfoService

    fun getCat(id : Int) : Flow<Result<CatDto>> = flow {
        Timber.d("${Thread.currentThread().name}")
        val call = getCatInfoService.getCat(id)
        try {
            Timber.d("request : ${call.request()}")
            val result : Response<CatDto> = call.execute()
            val responseBody = result.body()
            if (!result.isSuccessful || result.code() != 200 || responseBody == null) {
                emit(Result.failure(Exception("error : ${result.code()} , ${result.message()}")))
            } else {
                emit(Result.success(responseBody))
            }
        } catch (error : Exception) {
            Timber.d("error")
            emit(Result.failure(error))
        }
    }
}