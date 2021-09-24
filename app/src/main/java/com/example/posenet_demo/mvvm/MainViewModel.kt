package com.example.posenet_demo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posenet_demo.XYcoordinates

class MainViewModel : ViewModel() {
    val TAG = "MainViewModel"

    // MutableLiveData
    private val _xydata : MutableLiveData<XYdata> = MutableLiveData()
    // LiveData
    val xyLiveData: LiveData<XYdata> get() = _xydata

    fun init() {
        val xy = XYdata()
        _xydata.value = xy
    }

    /**
     * 좌표 데이터 변경 적용
     */
    fun changeXYData(newXY : XYdata) {
        /**
         * 새로운 객체를 적용 해야만 이벤트가 발생한다.
         */
        _xydata.postValue(newXY)
    }

    override fun onCleared() {
        Log.d(TAG, "## ViewModel - onCleared() called!!")
        super.onCleared()
    }
}