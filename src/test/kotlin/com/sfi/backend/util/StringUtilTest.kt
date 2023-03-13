package com.sfi.backend.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class StringUtilTest {

    companion object {
        private const val LENGTH = 26
    }

    @Test
    fun givenAsLength_whenExecutingRandomAlphanumericFunction_thenReturnAlphanumericString() {
        verifyTheSolution { i: Int -> StringUtil.randomAlphanumeric(i) }
    }

    @Test
    fun givenAsLengthIsZero_whenExecutingRandomAlphanumericFunction_thenReturnEmpty() {
        assertEquals("", StringUtil.randomAlphanumeric(0))
    }

    private fun verifyTheSolution(func: (length: Int) -> String) {
        val randomValues = List(100) { func(LENGTH) }

        assertTrue { randomValues.all { it.matches(Regex("^[a-zA-z0-9]+$")) } }  // 使用文字種の確認
        assertTrue { randomValues.none { it.length != LENGTH } }                        //  桁数の確認
        assertTrue { randomValues.size == randomValues.toSet().size }                   //  重複している値がないことを確認
    }

}