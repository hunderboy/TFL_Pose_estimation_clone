package com.example.posenet_demo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.posenet_demo.XYcoordinates.leftHip_x
import com.example.posenet_demo.XYcoordinates.leftHip_y
import com.example.posenet_demo.XYcoordinates.leftKnee_x
import com.example.posenet_demo.XYcoordinates.leftKnee_y
import com.example.posenet_demo.XYcoordinates.rightAnkle_x
import com.example.posenet_demo.XYcoordinates.rightAnkle_y
import com.example.posenet_demo.XYcoordinates.rightHip_x
import com.example.posenet_demo.XYcoordinates.rightHip_y
import com.example.posenet_demo.XYcoordinates.rightKnee_x
import com.example.posenet_demo.XYcoordinates.rightKnee_y
import com.example.posenet_demo.XYcoordinates.rightShoulder_x
import com.example.posenet_demo.XYcoordinates.rightShoulder_y
import com.example.posenet_demo.XYcoordinates.singConversion

import kotlin.math.atan2

class StandingSideRaiseModel() {

    private val TAG = "StandingSideRaiseModel"

    /**
     * 1. 스윙다리 각도 계산
     * 2개의 점으로 계산
     * 중앙점 = leftHip
     * 이동하는 점 = leftKnee
     */
    fun getDegree(context: Context){
        val paramY = leftKnee_y - leftHip_y
        val paramX = leftKnee_x - leftHip_x
        val degreeRadian = atan2(paramY,paramX)
        val degree = degreeRadian*(180.0 / Math.PI)
        val degreeReverse = singConversion((degree - 90)) // -90도 후 부호 변환
        Log.e(TAG,"오른쪽 허벅지 사이각 Radian : $degreeRadian")
        Log.e(TAG,"오른쪽 허벅지 사이각 : $degree")
        Log.e(TAG,"오른쪽 허벅지 사이각 반전 : $degreeReverse")

        Toast.makeText(context, "상체를 곧게 유지해 주세요", Toast.LENGTH_LONG).show()
        Toast.makeText(MyApplication.getApplicationContext(), "상체를 곧게 유지해 주세요", Toast.LENGTH_LONG).show()
    }

    /**
     * 2. 지탱다리 수직 체크
     * 3개의 점으로 계산
     * 중앙점 = RightKnee
     * 이동하는 점 1 = RightHip
     * 이동하는 점 2 = RightAnkle
     */
    fun getVerticalLegDegree(){
        val paramA2y = rightHip_y - rightKnee_y
        val paramA2x = rightHip_x - rightKnee_x
        val paramA1y = rightAnkle_y - rightKnee_y
        val paramA1x = rightAnkle_x - rightKnee_x
        val degreeRadian = atan2(paramA2y,paramA2x) - atan2(paramA1y,paramA1x)
        val degree = singConversion(degreeRadian * (180.0 / Math.PI))
        Log.e(TAG,"왼쪽 무릎 Radian : $degreeRadian")
        Log.e(TAG,"왼쪽 무릎 각도 : $degree")

        if(degree < 160){
            // Toast.makeText(MyApplication.getApplicationContext(), "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT).show()
        }

    }

    /**
     * 3. 상체 흐트러짐 체크
     * 2개의 점으로 계산
    왼쪽 허리각도
     * 중앙점 = rightHip
     * 이동하는 점 = rightShoulder
    오른쪽 허리각도
     * 중앙점 = leftHip
     * 이동하는 점 = leftShoulder
     */
    fun checkUpperBody(){
        // 오른쪽 허리 각도
//        val paramY_rightWaist = leftShoulder_y - leftHip_y
//        val paramX_rightWaist = leftShoulder_x - leftHip_x
//        val degreeRadian_rightWaist = atan2(paramY_rightWaist,paramX_rightWaist)
//        val degree_rightWaist = degreeRadian_rightWaist*(180.0 / Math.PI)
//        val degreeReverse_rightWaist = singConversion(degree_rightWaist) // 부호 변환
//        Log.e(TAG,"오른쪽 허리각 Radian : $degreeRadian_rightWaist")
//        Log.e(TAG,"오른쪽 허리각 각도 : $degree_rightWaist")
//        Log.e(TAG,"오른쪽 허리각 각도 반전 : $degreeReverse_rightWaist")

        // 왼쪽 허리 각도
        val paramY_leftWaist = rightShoulder_y - rightHip_y
        val paramX_leftWaist = rightShoulder_x - rightHip_x
        val degreeRadian_leftWaist = atan2(paramY_leftWaist,paramX_leftWaist)
        val degree_leftWaist = degreeRadian_leftWaist*(180.0 / Math.PI)
        val degreeReverse_leftWaist = 180+degree_leftWaist
        Log.e(TAG,"왼쪽 허리각 Radian : $degreeRadian_leftWaist")
        Log.e(TAG,"왼쪽 허리각 각도 : $degree_leftWaist")
        Log.e(TAG,"왼쪽 허리각 각도 반전 : $degreeReverse_leftWaist")

        // 한번 토스트를 띄우면 토스트가 꺼질때 까지 기다렸다가
        // 아직도 조건을 만족하면 다시 토스트를 띄운다. (계속 Toast를 뜨게하는것 방지)

        Toast.makeText(MyApplication.getApplicationContext(), "상체를 곧게 유지해 주세요", Toast.LENGTH_LONG).show()
//        if(degreeReverse_leftWaist.toInt() < 70){
//            Log.e(TAG,"들어감?")
//            if (toast==null){
//                Log.e(TAG,"들어감?null")
//                toast = Toast.makeText(context, "상체를 곧게 유지해 주세요", Toast.LENGTH_LONG)
//            }
//            toast?.show()
//         }
    }
}