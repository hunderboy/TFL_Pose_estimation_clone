package com.example.posenet_demo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.posenet_demo.data.BodyPart
import com.example.posenet_demo.data.Person


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





    /**
     * 라인과 포인트 그리는 함수 (Draw line and point indicate body pose)
     * 이부분을 거쳐야지만 화면에 관절과 뼈대를 그릴수 있음
     */
    fun drawBodyKeypoints(input: Bitmap, person: Person, context: Context): Bitmap {

        /**
         * 관절, 뼈대 를 그릴 포인트 준비
         */
        val paintCircle = Paint().apply { // 관절 포인트 (RED)
            strokeWidth = CIRCLE_RADIUS
            color = Color.RED
            style = Paint.Style.FILL
        }
        val paintLine = Paint().apply { // 뼈대 라인 (WHITE)
            strokeWidth = LINE_WIDTH
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        val output = input.copy(Bitmap.Config.ARGB_8888,true)
        val originalSizeCanvas = Canvas(output)

        /** forEach 반복문
         * 뼈대 라인 그리기
         */
        bodyJoints.forEach {
            val pointA = person.keyPoints[it.first.position].coordinate
            val pointB = person.keyPoints[it.second.position].coordinate
            // (시작) float startX, float startY, (끝) float stopX, float stopY
            originalSizeCanvas.drawLine(pointA.x, pointA.y, pointB.x, pointB.y, paintLine)
        }
        /** forEach 반복문
         * 관절 포인트 원형점 그리기
         */
        person.keyPoints.forEach { key_point ->
            originalSizeCanvas.drawCircle(
                key_point.coordinate.x, // 원형점을 그릴 x 좌표
                key_point.coordinate.y, // 원형점을 그릴 y 좌표
                CIRCLE_RADIUS,  // 반지름 길이 설정
                paintCircle     // 원형을 그리기 위한 미리 준비한 변수 적용
            )
            // 3가지 관절 포인트를 찾는다.
            when(key_point.bodyPart.toString()) {
                "LEFT_SHOULDER" -> { // 5
                    LegSwingModel.leftShoulder_x = key_point.coordinate.x
                    LegSwingModel.leftShoulder_y = key_point.coordinate.y
                }
                "RIGHT_SHOULDER" -> { // 6
                    LegSwingModel.rightShoulder_x = key_point.coordinate.x
                    LegSwingModel.rightShoulder_y = key_point.coordinate.y
                }
                "LEFT_ELBOW" -> { // 7
                    LegSwingModel.leftElbow_x = key_point.coordinate.x
                    LegSwingModel.leftElbow_y = key_point.coordinate.y
                }
                "RIGHT_ELBOW" -> {// 8
                    LegSwingModel.rightElbow_x = key_point.coordinate.x
                    LegSwingModel.rightElbow_y = key_point.coordinate.y
                }
                "LEFT_WRIST" -> {// 9
                    LegSwingModel.leftWrist_x = key_point.coordinate.x
                    LegSwingModel.leftWrist_y = key_point.coordinate.y
                }
                "RIGHT_WRIST" -> {// 10
                    LegSwingModel.rightWrist_x = key_point.coordinate.x
                    LegSwingModel.rightWrist_y = key_point.coordinate.y
                }
                "LEFT_HIP" -> { // 11
                    LegSwingModel.leftHip_x = key_point.coordinate.x
                    LegSwingModel.leftHip_y = key_point.coordinate.y
                }
                "RIGHT_HIP" -> { // 12
                    LegSwingModel.rightHip_x = key_point.coordinate.x
                    LegSwingModel.rightHip_y = key_point.coordinate.y
                }
                "LEFT_KNEE" -> { // 13
                    LegSwingModel.leftKnee_x = key_point.coordinate.x
                    LegSwingModel.leftKnee_y = key_point.coordinate.y
                }
                "RIGHT_KNEE" -> {// 14
                    LegSwingModel.rightKnee_x = key_point.coordinate.x
                    LegSwingModel.rightKnee_y = key_point.coordinate.y
                }
                "LEFT_ANKLE" -> {// 15
                    LegSwingModel.leftAnkle_x = key_point.coordinate.x
                    LegSwingModel.leftAnkle_y = key_point.coordinate.y
                }
                "RIGHT_ANKLE" -> {// 16
                    LegSwingModel.rightAnkle_x = key_point.coordinate.x
                    LegSwingModel.rightAnkle_y = key_point.coordinate.y
                }
            }
        }
        Log.e(TAG,"구분선 : ----------------------------------------------------------------")



        // 비트맵 1프레임을 완성했을때 마다 각도 계산해서 보여주기
//        LegSwingModel.getDegree()
//        LegSwingModel.getVerticalLegDegree()
        LegSwingModel.checkUpperBody(context)
        return output
    }



}