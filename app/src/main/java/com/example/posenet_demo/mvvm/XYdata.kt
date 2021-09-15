package com.example.posenet_demo.mvvm

data class XYdata(

    /**
     * 관절 좌표 x y
     */
    // 왼쪽 어깨 - 5
    var leftShoulder_x :Float = 0f,
    var leftShoulder_y :Float = 0f,
    // 오른쪽 어깨 - 6
    var rightShoulder_x :Float = 0f,
    var rightShoulder_y :Float = 0f,
    // 왼쪽 팔꿈치 - 7
    var leftElbow_x :Float = 0f,
    var leftElbow_y :Float = 0f,
    // 오른쪽 팔꿈치 - 8
    var rightElbow_x :Float = 0f,
    var rightElbow_y :Float = 0f,
    // 왼쪽 손목 - 9
    var leftWrist_x :Float = 0f,
    var leftWrist_y :Float = 0f,
    // 오른쪽 손목 - 10
    var rightWrist_x :Float = 0f,
    var rightWrist_y :Float = 0f,
    // 왼쪽 고관절 - 11
    var leftHip_x :Float = 0f,
    var leftHip_y :Float = 0f,
    // 오른쪽 고관절 - 12
    var rightHip_x :Float = 0f,
    var rightHip_y :Float = 0f,
    // 왼쪽 무릎 - 13
    var leftKnee_x :Float = 0f,
    var leftKnee_y :Float = 0f,
    // 오른쪽 무릎 - 14
    var rightKnee_x :Float = 0f,
    var rightKnee_y :Float = 0f,
    // 왼쪽 발목 - 15
    var leftAnkle_x :Float = 0f,
    var leftAnkle_y :Float = 0f,
    // 오른쪽 발목 - 16
    var rightAnkle_x :Float = 0f,
    var rightAnkle_y :Float = 0f,

)
