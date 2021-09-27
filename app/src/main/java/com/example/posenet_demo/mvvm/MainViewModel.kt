package com.example.posenet_demo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posenet_demo.XYcoordinates

/**
 * @author : 이성훈
 * @date : 2021-09-24
 * @description :
 * 메인화면 ViewModel
 */
class MainViewModel : ViewModel() {
    val TAG = "MainViewModel"

//    fun init() {
//        val xy = XYdata()
//        _xydata.value = xy
//    }

//    private val _xydata : MutableLiveData<XYdata> = MutableLiveData() // MutableLiveData
//    val xyLiveData: LiveData<XYdata> get() = _xydata                  // LiveData
//    /**
//     * 좌표 데이터 변경 적용
//     */
//    fun changeXYData(newXY : XYdata) {
//        /*** 새로운 객체를 적용 해야만 이벤트가 발생한다. */
//        _xydata.postValue(newXY)
//    }



    // 오른다리각도 LiveData
    private val _rightLegDegree : MutableLiveData<Double> = MutableLiveData() // MutableLiveData
    val rightLegDegreeLiveData: LiveData<Double> get() = _rightLegDegree      // LiveData
    // 오른다리각도 변경 함수
    fun noticeRightLegDegree(degree : Double) {
        _rightLegDegree.postValue(degree)
    }



    // 알림 Text LiveData
    private val _notice : MutableLiveData<String> = MutableLiveData() // MutableLiveData
    val noticeLiveData: LiveData<String> get() = _notice              // LiveData

    override fun onCleared() {
        Log.d(TAG, "## ViewModel - onCleared() called!!")
        super.onCleared()
    }
}