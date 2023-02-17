package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

class EndingRepository {

    val getEndingService = RetrofitObject.getEndingService

    fun getEnding00() : Flow<Result<String>> = flow {
        Timber.d("${Thread.currentThread().name}")
        val call = getEndingService.getEnding00()
        try {
            Timber.d("request : ${call.request()}")
            val result : Response<String> = call.execute()
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