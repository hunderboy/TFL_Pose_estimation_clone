package com.example.posenet_demo

import android.util.Log
import kotlin.math.atan2


object LegSwingModel {

    private const val TAG = "LegSwingModel"

    /**
     * 관절 좌표 x y
     */
    // 왼쪽 고관절
    var leftHip_x :Float = 0f
    var leftHip_y :Float = 0f
    // 오른쪽 고관절
    var rightHip_x :Float = 0f
    var rightHip_y :Float = 0f
    // 오른쪽 무릎
    var rightKnee_x :Float = 0f
    var rightKnee_y :Float = 0f
    // 왼쪽 무릎
    var leftKnee_x :Float = 0f
    var leftKnee_y :Float = 0f
    // 왼쪽 발목
    var leftAnkle_x :Float = 0f
    var leftAnkle_y :Float = 0f

    /**
     * 고관절과 오른쪽 다리의 내측 각도 계산
     */
    fun getDegree(){

        val point_1_Y = leftHip_y - rightHip_y
        val point_1_X = leftHip_x - rightHip_x
        val degree_1_radian = atan2(point_1_Y,point_1_X)
        val degree1 = degree_1_radian*(180.0 / Math.PI)
        Log.e(TAG,"degree1 각도 : $degree1")
        val point_2_Y = rightKnee_y - rightHip_y
        val point_2_X = rightKnee_x - rightHip_x
        val degree_2_radian = atan2(point_2_Y,point_2_X)
        val degree2 = degree_2_radian*(180.0 / Math.PI)
        Log.e(TAG,"degree2 각도 : $degree2")


//        val degreeRadian = atan2((rightKnee_y-rightHip_y),(rightKnee_x-rightHip_x)) - atan2((leftHip_y-rightHip_y),(leftHip_x-rightHip_x))
//        val degree = degreeRadian*(180.0 / Math.PI)
//        Log.e(TAG,"오른쪽 고관절 Radian : $degreeRadian")
//        Log.e(TAG,"오른쪽 고관절 각도 : $degree")
    }

    /**
     * 다리는 수직으로 되어 있는지?
     */
    fun getVerticalLegDegree(){
//        val degreeRadian = atan2((leftHip_y-leftKnee_y),(leftHip_x-leftKnee_x)) - atan2((leftAnkle_y-leftKnee_y),(leftAnkle_x-leftKnee_x))
//        val degree = degreeRadian*(180.0 / Math.PI)
//
//        Log.e(TAG,"왼쪽 무릎 Radian : $degreeRadian")
//        Log.e(TAG,"왼쪽 무릎 각도 : $degree")

        val degreeRadian = atan2((leftKnee_y - leftHip_y),(leftKnee_x - leftHip_x))
        val degree = degreeRadian*(180.0 / Math.PI)
        val degreeReverse = (degree-90)*-1

        Log.e(TAG,"오른쪽 허리각 Radian : $degreeRadian")
        Log.e(TAG,"오른쪽 허리각 각도 : $degree")
        Log.e(TAG,"오른쪽 허리각 각도 반전 : $degreeReverse")
    }
    
}