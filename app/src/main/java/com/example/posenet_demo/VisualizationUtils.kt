package com.example.posenet_demo

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.posenet_demo.data.BodyPart
import com.example.posenet_demo.data.Person
import kotlin.math.atan2

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

    private var leftHip_x :Float = 0f
    private var leftHip_y :Float = 0f
    private var rightHip_x :Float = 0f
    private var rightHip_y :Float = 0f
    private var rightKnee_x :Float = 0f
    private var rightKnee_y :Float = 0f



    /** Draw line and point indicate body pose
     * 라인과 포인트 그리는 함수
     * 이부분을 거쳐야지만 최종적으로 화면에 그릴수 있음
     */
    fun drawBodyKeypoints(input: Bitmap, person: Person): Bitmap {

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
                "LEFT_HIP" -> {
                    leftHip_x = key_point.coordinate.x
                    leftHip_y = key_point.coordinate.y
                    Log.e(TAG,"BodyPart : ${key_point.bodyPart}")
                    Log.e(TAG,"x : ${key_point.coordinate.x}")
                    Log.e(TAG,"y : ${key_point.coordinate.y}")
                }
                "RIGHT_HIP" -> {        // 중간 기준점
                    rightHip_x = 0f
                    rightHip_y = 0f
                    Log.e(TAG,"BodyPart : ${key_point.bodyPart}")
                    Log.e(TAG,"x : ${key_point.coordinate.x}")
                    Log.e(TAG,"y : ${key_point.coordinate.y}")

                }
                "RIGHT_KNEE" -> {
                    rightKnee_x = 0f
                    rightKnee_y = 0f
                    Log.e(TAG,"BodyPart : ${key_point.bodyPart}")
                    Log.e(TAG,"x : ${key_point.coordinate.x}")
                    Log.e(TAG,"y : ${key_point.coordinate.y}")

                }
            }
            getDegree()
        }

        return output
    }



    /**
     * 고관절과 오른쪽 다리의 내측 각도 계산
     */
    fun getDegree(){
        val degreeRadian = atan2((rightKnee_y-rightHip_y),(rightKnee_x-rightHip_x)) - atan2((leftHip_y-rightHip_y),(leftHip_x-rightHip_x))
        val degree = degreeRadian*(180.0 / Math.PI)

        Log.e(TAG,"각도 : $degree")
    }

}