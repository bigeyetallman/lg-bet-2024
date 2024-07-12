package com.bigeyetallman.welcometopranking.utils

class Values {
    var playerPointMap = mutableMapOf<String, Double>(
        "구본혁" to 0.0,
        "김윤식" to 0.0,
        "김현수" to 0.0,
        "문보경" to 0.0,
        "문성주" to 0.0,
        "박명근" to 0.0,
        "박해민" to 0.0,
        "신민재" to 0.0,
        "오스틴" to 0.0,
        "오지환" to 0.0,
        "유영찬" to 0.0,
        "유영찬" to 0.0,
        "윤호솔" to 0.0,
        "이지강" to 0.0,
        "정지헌" to 0.0,
        "홍창기" to 0.0,
    )

    var newPlayerPointMap = mutableMapOf<String, Double>(
        "켈리" to 0.0,
        "이상영" to 0.0,
        "손주영" to 0.0,
    )

    var userTotalPointMap = mutableMapOf<String, Double>(
        "김동진" to 0.0,
        "안성진" to 0.0,
        "정찬웅" to 0.0,
        "지용현" to 0.0,
        "최정명" to 0.0,
        "황유진" to 0.0,
    )

    companion object {
        val playerSelectMap = mapOf<String, Array<String>>(
            "김동진" to arrayOf("김현수", "오지환", "이지강", "구본혁"),
            "안성진" to arrayOf("오스틴", "문보경", "유영찬"),
            "정찬웅" to arrayOf("김현수", "문보경", "유영찬"),
            "지용현" to arrayOf("김현수", "박해민", "유영찬", "이지강", "구본혁"),
            "최정명" to arrayOf("김현수", "유영찬", "박명근"),
            "황유진" to arrayOf("홍창기", "문성주", "신민재"),
        )

        val newPlayerSelectMap = mapOf<String, Array<String>>(
            "김동진" to arrayOf("켈리"),
            "정찬웅" to arrayOf("켈리"),
            "지용현" to arrayOf("이상영"),
            "최정명" to arrayOf("켈리", "손주영"),
        )

        val removePlayerSelectMap = mapOf<String, Array<String>>(
            "김동진" to arrayOf("김윤식"),
            "정찬웅" to arrayOf("이지강", "윤호솔"),
            "지용현" to arrayOf("정지헌"),
            "최정명" to arrayOf("오지환"),
        )

        val oldScoreMap = mapOf<String, Double>(
            "윤호솔" to -21.15,
            "이지강" to -83.79,
            "오지환" to 229.95,
            "정지헌" to -95.23,
            "김윤식" to -51.50,
            "켈리" to 180.84,
            "이상영" to 51.51,
            "손주영" to 210.27,
        )
    }
}