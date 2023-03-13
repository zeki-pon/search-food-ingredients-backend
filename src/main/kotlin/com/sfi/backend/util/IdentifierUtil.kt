package com.sfi.backend.util

object IdentifierUtil {
    /**
     * 指定された文字数でIDを生成する。
     * IDの形式：${prefix}${乱数([a-zA-Z0-9])}
     * 例：prefix="test_", length=10の場合、test_aB123
     */
    fun generateId(prefix: String, randomValueLength: Int): String {
        return "${prefix}${StringUtil.randomAlphanumeric(randomValueLength)}"
    }
}