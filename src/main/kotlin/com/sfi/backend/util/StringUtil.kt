package com.sfi.backend.util


object StringUtil {

    private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    /**
     * 指定された桁数のランダムな文字列([a-zA-Z0-9])を生成する
     */
    fun randomAlphanumeric(length: Int): String {
//        return (1..length).map { Random.nextInt(0, charPool.size).let { charPool[it] } }.joinToString(separator = "")
        return List(length) { charPool.random() }.joinToString(separator = "")
    }
}