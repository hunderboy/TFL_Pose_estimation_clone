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


    // 1. 오른 다리 각도 LiveData
    private val _rightLegDegree : MutableLiveData<Double> = MutableLiveData()
    val rightLegDegreeLiveData: LiveData<Double> get() = _rightLegDegree
    // 오른다리각도 변경 함수
    fun noticeRightLegDegree(degree : Double) {
        _rightLegDegree.postValue(degree)
    }

    // 2. 수직 다리 각도 LiveData
    private val _verticalLegDegree : MutableLiveData<Double> = MutableLiveData()
    val verticalLegDegreeLiveData: LiveData<Double> get() = _verticalLegDegree
    // 수직 다리 각도 변경 함수
    fun noticeVerticalLegDegree(degree : Double) {
        _verticalLegDegree.postValue(degree)
    }

    // 3. 왼쪽 상체 허리 각도 LiveData
    private val _leftWaistDegree : MutableLiveData<Double> = MutableLiveData()
    val leftWaistDegreeLiveData: LiveData<Double> get() = _leftWaistDegree
    fun noticeLeftWaistDegree(degree : Double) {
        _leftWaistDegree.postValue(degree)
    }

    // 4. 오른쪽 상체 허리 각도 LiveData
    private val _rightWaistDegree : MutableLiveData<Double> = MutableLiveData()
    val rightWaistDegreeLiveData: LiveData<Double> get() = _rightWaistDegree
    fun noticeRightWaistDegree(degree : Double) {
        _rightWaistDegree.postValue(degree)
    }


    override fun onCleared() {
        Log.d(TAG, "## ViewModel - onCleared() called!!")
        super.onCleared()
    }
}