/* Copyright 2021 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================
*/

package com.example.posenet_demo.data

enum class BodyPart(val position: Int) {

    /**
     * 총 17(0~16)개의 point dot
     */
    NOSE(0),
    LEFT_EYE(1),
    RIGHT_EYE(2),
    LEFT_EAR(3),
    RIGHT_EAR(4),
    LEFT_SHOULDER(5),   // 어깨 (왼쪽)
    RIGHT_SHOULDER(6),  // 어깨 (오른쪽)
    LEFT_ELBOW(7),      // 팔꿈치 (왼쪽)
    RIGHT_ELBOW(8),     // 팔꿈치 (오른쪽)
    LEFT_WRIST(9),      // 손목 (왼쪽)
    RIGHT_WRIST(10),    // 손목 (오른쪽)
    LEFT_HIP(11),       // 골반 (왼쪽)
    RIGHT_HIP(12),      // 골반 (오른쪽)
    LEFT_KNEE(13),      // 무릎 (왼쪽)
    RIGHT_KNEE(14),     // 무릎 (오른쪽)
    LEFT_ANKLE(15),     // 발목 (왼쪽)
    RIGHT_ANKLE(16);    // 발목 (오른쪽)

    companion object{
        /**
         * 여기서 map = array 이며
         * position = key 에 따라
         * LEFT_SHOULDER 같은 value를 추출하겠다라 는것
         * 만약,
         * fromInt(5) => LEFT_SHOULDER 반환
         */
        private val map = values().associateBy(BodyPart::position)
        fun fromInt(position: Int): BodyPart = map.getValue(position)
    }
}
