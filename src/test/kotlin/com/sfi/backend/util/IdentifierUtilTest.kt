package com.sfi.backend.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class IdentifierUtilTest {
    companion object {
        private const val PREFIX = "test_"
        private const val RANDOM_VALUE_LENGTH = 26
    }

    @Test
    fun givenAsPrefixAndLength_whenExecutingGenerateIdFunction_thenReturnIdContainingAlphanumericString() {
        verifyTheSolution { s: String, i: Int -> IdentifierUtil.generateId(s, i) }
    }

    @Test
    fun givenAsEmptyAndZero_whenExecutingGenerateIdFunction_thenReturnEmpty() {
        assertEquals("", IdentifierUtil.generateId("",0))
    }


    private fun verifyTheSolution(func: (prefix: String, length: Int) -> String) {
        val ids = List(100) { func(PREFIX,RANDOM_VALUE_LENGTH) }

        assertTrue { ids.all { it.matches(Regex("${PREFIX}[a-zA-z0-9]+$")) } }  // 使用文字種の確認
        assertTrue { ids.none { it.length != PREFIX.length + RANDOM_VALUE_LENGTH } }    //  桁数の確認
        assertTrue { ids.size == ids.toSet().size }                                     //  重複している値がないことを確認
    }
}