package com.example.kotlin.language.sam

import org.junit.jupiter.api.Test

class SamTest {
    fun interface SelfRunnable {
        fun run()
    }

    fun setRunnable(selfRunnable: SelfRunnable) {
        selfRunnable.run()
    }

    @Test
    fun samTest() {
        setRunnable {
            println("setRunnable")
        }
    }
}
