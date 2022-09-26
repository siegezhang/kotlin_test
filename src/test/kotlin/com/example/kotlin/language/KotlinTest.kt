package com.example.kotlin.language

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KotlinTest {
    @Test
    fun testSum() {
        val expected = 42
        assertEquals(expected, 42)
    }

    @Test
    fun testSum1() {
        val str: String? = null
        when (str) {
        }
        println(str)
    }

    @Test
    fun testFn() {
        val p = { x: Int -> x }
        println(testFun(p, 10))
    }

    @Test
    fun testNullInvoke() {
        val name: String? = null
        println(name.isNullOrEmpty()) //true
    }

    fun testFun(fn: (Int) -> Int, age: Int): Int {
        return fn(age)
    }

    interface Printer {

        fun print()

    }

    class DefaultPrinter : Printer {

        override fun print() {
            println("DefaultPrinter print")
        }

    }

    class CustomPrinter(val printer: Printer) : Printer by printer

    @Test
    fun testDelegate() {
        val printer = CustomPrinter(DefaultPrinter())
        printer.print() //DefaultPrinter print
    }
}