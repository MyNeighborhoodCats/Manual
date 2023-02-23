package kr.co.vacatio.ylee.my_neighborhood_cats.manual.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber

class IntroRepository {

    val getIntroService = RetrofitObject.getIntroService

    fun getGameIntro() : Flow<Result<String>> = flow {
        Timber.d("${Thread.currentThread().name}")
        val call = getIntroService.getGameIntro()
        try {
            Timber.d("request : ${call.request()}")
            val result : Response<TextResponse> = call.execute()
            val responseBody = result.body()
            if (!result.isSuccessful || result.code() != 200 || responseBody == null) {
                emit(Result.failure(Exception("error : ${result.code()} , ${result.message()}")))
            } else if (responseBody.result == null) {
                emit(Result.failure(Exception("error : ${result.code()} , result is null")))
            } else {
                emit(Result.success(responseBody.result))
            }
        } catch (error : Exception) {
            Timber.d("error")
            emit(Result.failure(error))
        }
    }
}