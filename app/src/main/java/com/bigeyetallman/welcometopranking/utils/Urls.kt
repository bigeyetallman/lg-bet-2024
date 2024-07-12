package com.bigeyetallman.welcometopranking.utils

class Urls {

    companion object {
        fun getPitcherUrl(page: Int): String {
            val pitcherUrl =
                "http://www.welcometopranking.com/baseball/?p=chart&searchType=YEARLY&searchDate=2024&position=1&team=5002&page=1&orderBy=&orderSort="
            return "$pitcherUrl&page=$page"
        }

        fun getHitterUrl(page: Int): String {
            val hitterUrl =
                "http://www.welcometopranking.com/baseball/?p=chart&searchType=YEARLY&searchDate=2024&position=T&team=5002&page=1&orderBy=&orderSort="
            return "$hitterUrl&page=$page"
        }
    }
}