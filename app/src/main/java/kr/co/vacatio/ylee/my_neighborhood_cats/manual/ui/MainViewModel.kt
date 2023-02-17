package kr.co.vacatio.ylee.my_neighborhood_cats.manual.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kr.co.vacatio.ylee.my_neighborhood_cats.manual.data.IntroRepository
import timber.log.Timber

class MainViewModel : ViewModel() {

    val introRepository : IntroRepository = IntroRepository()

    private val _fragmentName : MutableLiveData<String> = MutableLiveData()
    val fragmentName : LiveData<String> = _fragmentName

    fun setFragment(fragmentName : String) {
        _fragmentName.value = fragmentName
    }

    private val _gameIntro : MutableLiveData<String> = MutableLiveData()
    val gameIntro : LiveData<String> = _gameIntro

    fun getGameIntro() {
        CoroutineScope(Dispatchers.Main).launch {
            introRepository.getGameIntro().flowOn(Dispatchers.IO).collect {
                if (it.isFailure) {
                    val error = it.exceptionOrNull()
                    Timber.e("isFailure : ${error?.message}")
                    return@collect
                }
                val responseBody = it.getOrNull()
                if (responseBody == null) {
                    return@collect
                }
                Timber.d("response body : ${responseBody.toString()}")
                _gameIntro.value = responseBody
            }
        }
    }
}