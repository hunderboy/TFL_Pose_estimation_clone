package com.example.posenet_demo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.posenet_demo.XYcoordinates

class MainViewModel : ViewModel() {
    val TAG = "ViewModel"

    private var count = 0
    // 뮤터블 라이브 데이터 생성 - get,set 가능
    private val _countText : MutableLiveData<String> = MutableLiveData()
    // View 단에서 읽기 접근만 가능하게
    val liveData: LiveData<String> get() = _countText


    fun init() {
        _countText.value = "click count : $count"
    }

    fun clickButton() {
        _countText.value = "click count : ${++count}"
    }

// ------------------------------------------------------------------------------------------------

    // MutableLiveData
    private val _xydata : MutableLiveData<XYdata> = MutableLiveData()
    // LiveData
    val xyLiveData: LiveData<XYdata> get() = _xydata


    /**
     * 좌표 데이터 변경 적용
     */
    fun changeXYData() {
        Log.e(TAG,"changeXYData() : 들어옴")
// 왼쪽 어깨 - 5
        _xydata.value?.leftShoulder_x = XYcoordinates.leftShoulder_x
        _xydata.value?.leftShoulder_y = XYcoordinates.leftShoulder_y
// 오른쪽 어깨 - 6
        _xydata.value?.rightShoulder_x = XYcoordinates.rightShoulder_x
        _xydata.value?.rightShoulder_y = XYcoordinates.rightShoulder_y
// 왼쪽 팔꿈치 - 7
        _xydata.value?.leftElbow_x = XYcoordinates.leftElbow_x
        _xydata.value?.leftElbow_y = XYcoordinates.leftElbow_y
// 오른쪽 팔꿈치 - 8
        _xydata.value?.rightElbow_x = XYcoordinates.rightElbow_x
        _xydata.value?.rightElbow_y = XYcoordinates.rightElbow_y
// 왼쪽 손목 - 9
        _xydata.value?.leftWrist_x = XYcoordinates.leftWrist_x
        _xydata.value?.leftWrist_y = XYcoordinates.leftWrist_y
// 오른쪽 손목 - 10
        _xydata.value?.rightWrist_x = XYcoordinates.rightWrist_x
        _xydata.value?.rightWrist_y = XYcoordinates.rightWrist_y
// 왼쪽 고관절 - 11
        _xydata.value?.leftHip_x = XYcoordinates.leftHip_x
        _xydata.value?.leftHip_y = XYcoordinates.leftHip_y
// 오른쪽 고관절 - 12
        _xydata.value?.rightHip_x = XYcoordinates.rightHip_x
        _xydata.value?.rightHip_y = XYcoordinates.rightHip_y
// 왼쪽 무릎 - 13
        _xydata.value?.leftKnee_x = XYcoordinates.leftKnee_x
        _xydata.value?.leftKnee_y = XYcoordinates.leftKnee_y
// 오른쪽 무릎 - 14
        _xydata.value?.rightKnee_x = XYcoordinates.rightKnee_x
        _xydata.value?.rightKnee_y = XYcoordinates.rightKnee_y
// 왼쪽 발목 - 15
        _xydata.value?.leftAnkle_x = XYcoordinates.leftAnkle_x
        _xydata.value?.leftAnkle_y = XYcoordinates.leftAnkle_y
// 오른쪽 발목 - 16
        _xydata.value?.rightAnkle_x = XYcoordinates.rightAnkle_x
        _xydata.value?.rightAnkle_y = XYcoordinates.rightAnkle_y

        // viewModelScope.launch {}
    }

    override fun onCleared() {
        Log.d(TAG, "## ViewModel - onCleared() called!!")
        super.onCleared()
    }
}