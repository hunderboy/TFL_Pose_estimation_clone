package com.example.posenet_demo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.posenet_demo.data.BodyPart
import com.example.posenet_demo.data.Person
import com.example.posenet_demo.mvvm.MainViewModel
import com.example.posenet_demo.mvvm.XYdata


/**
 * 시각화 자료
 */
object VisualizationUtils {
    private const val TAG = "VisualizationUtils"

    /** Radius of circle used to draw keypoints.  */
    private const val CIRCLE_RADIUS = 6f // 원형의 반지름 길이 설정

    /** Width of line used to connected two keypoints.  */
    private const val LINE_WIDTH = 4f   // 라인의 굵기 길이 설정

    /** Pair of keypoints to draw lines between.  */
    private val bodyJoints = listOf(
        Pair(BodyPart.NOSE, BodyPart.LEFT_EYE),
        Pair(BodyPart.NOSE, BodyPart.RIGHT_EYE),
        Pair(BodyPart.LEFT_EYE, BodyPart.LEFT_EAR),
        Pair(BodyPart.RIGHT_EYE, BodyPart.RIGHT_EAR),
        Pair(BodyPart.NOSE, BodyPart.LEFT_SHOULDER),
        Pair(BodyPart.NOSE, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_ELBOW),
        Pair(BodyPart.LEFT_ELBOW, BodyPart.LEFT_WRIST),
        Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_ELBOW),
        Pair(BodyPart.RIGHT_ELBOW, BodyPart.RIGHT_WRIST),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.RIGHT_SHOULDER),
        Pair(BodyPart.LEFT_SHOULDER, BodyPart.LEFT_HIP),
        Pair(BodyPart.RIGHT_SHOULDER, BodyPart.RIGHT_HIP),
        Pair(BodyPart.LEFT_HIP, BodyPart.RIGHT_HIP),
        Pair(BodyPart.LEFT_HIP, BodyPart.LEFT_KNEE),
        Pair(BodyPart.LEFT_KNEE, BodyPart.LEFT_ANKLE),
        Pair(BodyPart.RIGHT_HIP, BodyPart.RIGHT_KNEE),
        Pair(BodyPart.RIGHT_KNEE, BodyPart.RIGHT_ANKLE)
    )

    // 관절 xy 좌표 (매번 frame 이 변경될때 마다 새로운 관절 xy 좌표 가 설정됨 )
    private val newXY = XYdata()

    // 스탠딩사이드레그레이즈
    private val standingSideRaiseModel = StandingSideRaiseModel()

    /**
     * 라인과 포인트 그리는 함수 (Draw line and point indicate body pose)
     * 이부분을 거쳐야지만 화면에 관절과 뼈대를 그릴수 있음
     */
    fun drawBodyKeypoints(input: Bitmap, person: Person, viewModel: MainViewModel): Bitmap {

        // 관절 포인트 (RED)
        val paintCircle = Paint().apply {
            strokeWidth = CIRCLE_RADIUS
            color = Color.RED
            style = Paint.Style.FILL
        }
        // 뼈대 라인 (WHITE)
        val paintLine = Paint().apply {
            strokeWidth = LINE_WIDTH
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        val output = input.copy(Bitmap.Config.ARGB_8888, true)
        val originalSizeCanvas = Canvas(output)


        // 뼈대 라인 그리기
        bodyJoints.forEach {
            val pointA = person.keyPoints[it.first.position].coordinate
            val pointB = person.keyPoints[it.second.position].coordinate
            // (시작) float startX, float startY, (끝) float stopX, float stopY
            originalSizeCanvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paintLine)
        }

        // 관절 포인트 원형점 그리기
        person.keyPoints.forEach { key_point ->
            originalSizeCanvas.drawCircle(
                key_point.coordinate.x, // 원형점을 그릴 x 좌표
                key_point.coordinate.y, // 원형점을 그릴 y 좌표
                CIRCLE_RADIUS,  // 반지름 길이 설정
                paintCircle     // 원형을 그리기 위한 미리 준비한 변수 적용
            )


            // 관절 포인트의 명칭에 따른 x,y 좌표
            when (key_point.bodyPart.toString()) {
                "LEFT_SHOULDER" -> { // 5
                    newXY.leftShoulder_x = key_point.coordinate.x
                    newXY.leftShoulder_y = key_point.coordinate.y
                }
                "RIGHT_SHOULDER" -> { // 6
                    newXY.rightShoulder_x = key_point.coordinate.x
                    newXY.rightShoulder_y = key_point.coordinate.y
                }
                "LEFT_ELBOW" -> { // 7
                    newXY.leftElbow_x = key_point.coordinate.x
                    newXY.leftElbow_y = key_point.coordinate.y
                }
                "RIGHT_ELBOW" -> {// 8
                    newXY.rightElbow_x = key_point.coordinate.x
                    newXY.rightElbow_y = key_point.coordinate.y
                }
                "LEFT_WRIST" -> {// 9
                    newXY.leftWrist_x = key_point.coordinate.x
                    newXY.leftWrist_y = key_point.coordinate.y
                }
                "RIGHT_WRIST" -> {// 10
                    newXY.rightWrist_x = key_point.coordinate.x
                    newXY.rightWrist_y = key_point.coordinate.y
                }
                "LEFT_HIP" -> { // 11
                    newXY.leftHip_x = key_point.coordinate.x
                    newXY.leftHip_y = key_point.coordinate.y
                }
                "RIGHT_HIP" -> { // 12
                    newXY.rightHip_x = key_point.coordinate.x
                    newXY.rightHip_y = key_point.coordinate.y
                }
                "LEFT_KNEE" -> { // 13
                    newXY.leftKnee_x = key_point.coordinate.x
                    newXY.leftKnee_y = key_point.coordinate.y
                }
                "RIGHT_KNEE" -> {// 14
                    newXY.rightKnee_x = key_point.coordinate.x
                    newXY.rightKnee_y = key_point.coordinate.y
                }
                "LEFT_ANKLE" -> {// 15
                    newXY.leftAnkle_x = key_point.coordinate.x
                    newXY.leftAnkle_y = key_point.coordinate.y
                }
                "RIGHT_ANKLE" -> {// 16
                    newXY.rightAnkle_x = key_point.coordinate.x
                    newXY.rightAnkle_y = key_point.coordinate.y
                }
            }
        }// when 끝 = 관절 xy 좌표 데이터 설정 완료

        // 관절 xy 좌표 가 설정되면, 해당 좌표값들을 가지고
        // 1. 오른 다리 사이각 계산
        val rightLegInnerDegrees = standingSideRaiseModel.getRightLegInnerDegrees(newXY)
        // 2. 수직 다리 체크 각도
        val legVerticalAngle = standingSideRaiseModel.getVerticalLegDegree(newXY)
        // 3. 허리각도 체크 (왼쪽)
        val leftWaistAngles = standingSideRaiseModel.checkLeftWaistDegree(newXY)
        // 4. 허리각도 체크 (오른쪽)
        val rightWaistAngles = standingSideRaiseModel.checkRightWaistDegree(newXY)

        /*** viewModel 의 데이터 각도 변화 이벤트 전달 */
        viewModel.noticeRightLegDegree(rightLegInnerDegrees)
        viewModel.noticeVerticalLegDegree(legVerticalAngle)
        viewModel.noticeLeftWaistDegree(leftWaistAngles)
        viewModel.noticeRightWaistDegree(rightWaistAngles)

        return output
    }// drawBodyKeypoints 끝


}