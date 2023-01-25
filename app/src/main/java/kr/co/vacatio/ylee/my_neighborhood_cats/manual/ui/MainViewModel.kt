package kr.co.vacatio.ylee.my_neighborhood_cats.manual.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _fragmentName : MutableLiveData<String> = MutableLiveData()
    val fragmentName : LiveData<String> = _fragmentName

    fun setFragment(fragmentName : String) {
        _fragmentName.value = fragmentName
    }
}