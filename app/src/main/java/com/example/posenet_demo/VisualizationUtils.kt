package com.example.posenet_demo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.posenet_demo.data.BodyPart
import com.example.posenet_demo.data.Person
import com.example.posenet_demo.mvvm.MainViewModel


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
    fun drawBodyKeypoints(input: Bitmap, person: Person, viewModel: MainViewModel): Bitmap {

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

        val output = input.copy(Bitmap.Config.ARGB_8888, true)
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
            when (key_point.bodyPart.toString()) {
                "LEFT_SHOULDER" -> { // 5
                    XYcoordinates.leftShoulder_x = key_point.coordinate.x
                    XYcoordinates.leftShoulder_y = key_point.coordinate.y
                }
                "RIGHT_SHOULDER" -> { // 6
                    XYcoordinates.rightShoulder_x = key_point.coordinate.x
                    XYcoordinates.rightShoulder_y = key_point.coordinate.y
                }
                "LEFT_ELBOW" -> { // 7
                    XYcoordinates.leftElbow_x = key_point.coordinate.x
                    XYcoordinates.leftElbow_y = key_point.coordinate.y
                }
                "RIGHT_ELBOW" -> {// 8
                    XYcoordinates.rightElbow_x = key_point.coordinate.x
                    XYcoordinates.rightElbow_y = key_point.coordinate.y
                }
                "LEFT_WRIST" -> {// 9
                    XYcoordinates.leftWrist_x = key_point.coordinate.x
                    XYcoordinates.leftWrist_y = key_point.coordinate.y
                }
                "RIGHT_WRIST" -> {// 10
                    XYcoordinates.rightWrist_x = key_point.coordinate.x
                    XYcoordinates.rightWrist_y = key_point.coordinate.y
                }
                "LEFT_HIP" -> { // 11
                    XYcoordinates.leftHip_x = key_point.coordinate.x
                    XYcoordinates.leftHip_y = key_point.coordinate.y
                }
                "RIGHT_HIP" -> { // 12
                    XYcoordinates.rightHip_x = key_point.coordinate.x
                    XYcoordinates.rightHip_y = key_point.coordinate.y
                }
                "LEFT_KNEE" -> { // 13
                    XYcoordinates.leftKnee_x = key_point.coordinate.x
                    XYcoordinates.leftKnee_y = key_point.coordinate.y
                }
                "RIGHT_KNEE" -> {// 14
                    XYcoordinates.rightKnee_x = key_point.coordinate.x
                    XYcoordinates.rightKnee_y = key_point.coordinate.y
                }
                "LEFT_ANKLE" -> {// 15
                    XYcoordinates.leftAnkle_x = key_point.coordinate.x
                    XYcoordinates.leftAnkle_y = key_point.coordinate.y
                }
                "RIGHT_ANKLE" -> {// 16
                    XYcoordinates.rightAnkle_x = key_point.coordinate.x
                    XYcoordinates.rightAnkle_y = key_point.coordinate.y
                }
            }
        }

        // ViewModel 의 LiveData 에 데이터 변경
        viewModel.changeXYData()


        return output
    }// drawBodyKeypoints 끝


}