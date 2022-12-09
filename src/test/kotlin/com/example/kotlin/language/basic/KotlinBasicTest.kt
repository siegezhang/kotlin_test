package com.example.kotlin.language.basic

import org.junit.jupiter.api.Test

class KotlinBasicTest {
    @Test
    fun testIn() {
        for (i in 0..10 step 2)
            println(i)
    }

    @Test
    fun testDownTo() {
        for (i in 10 downTo 0)
            println(i)
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testOpenEnded() {
        var value = 0.6
        when (value) {
            in 0.0..<0.25 ->1.0 // First quarter
            in 0.25..<0.5 ->1 // Second quarter
            in 0.5..<0.75 ->3 // Third quarter
            in 0.75..1.0 ->4  // Last quarter  <- Note closed range here
        }
    }
}