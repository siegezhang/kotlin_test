package com.example.kotlin.language.array

import org.junit.jupiter.api.Test

class ArrayTest {
    @Test
    fun testArray1() {
        val asc = Array(5) { i -> (i * i).toString() }
        asc.forEach { println(it) }
    }
}