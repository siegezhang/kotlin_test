package com.example.kotlin.language.scope

import org.junit.jupiter.api.Test

class ScopeTest {
    @Test
    fun runScopeFunctions() {
        val str = "ss007"

        val size1: Int = str.apply {
            println(this)
        }.length

        val size2: Int = str.also {
            println(it)
        }.length

        val size3: Int = str.let {
            println(it)
            it.length
        }
        val size4: Int = str.run {
            println(this)
            this.length
        }
        val size5: Int = with(str) {
            println(this)
            this.length
        }

        val size6: Int = run {
            println(str)
            str
        }.length
    }

}