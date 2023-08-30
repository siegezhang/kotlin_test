package com.example.kotlin.language

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*
import kotlinx.coroutines.*
*/


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

    @ExperimentalContracts
    fun calledOneTimeOnly(run: () -> Unit) {
        contract {
            callsInPlace(run, InvocationKind.EXACTLY_ONCE)
        }
        run()
    }

    @ExperimentalContracts
    fun initValue() {
        val intValue: Int
        calledOneTimeOnly {
            // Does not compile:
            // Captured values initialization is forbidden due to possible reassignment.
            intValue = 1
        }
//        calledOneTimeOnly {
//            intValue = 2
//        }
    }

//    fun getString(): String? {
//        return null
//    }

//    @ExperimentalContracts
//    fun Any?.isValidString(): Boolean {
//        contract {
//            returns(true) implies (this@getString is String)
//        }
//        return this != null && this is String && this.length > 0
//    }
//
//    @ExperimentalContracts
//    fun testString() {
//        val test = getString()
//
//        if (test.isValidString()) {
//            // Compiles.
//            val result: String = test
//        }
//    }

    /**
     * 字符串扩展函数判空，常规方式
     * @receiver String?  接收类型
     * @return Boolean    是否为空
     */
    fun String?.isNullOrEmptyWithoutContract(): Boolean {
        return this == null || this.isEmpty()
    }
//
//    /**
//     * 问题示例1 使用自定义函数判空，编译器无感知
//     * @param name String? 传入的姓名字符串
//     */
//    private fun problemNull(name: String?) {
//        // 用常规方式的自定义扩展函数对局部变量判空
//        if (!name.isNullOrEmptyWithoutContract()) {
//            //name.length报错，自定义扩展函数中的判空逻辑未同步到编译器 Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
//            println("name:$name,length:${name.length}")
//        }
//    }

    /**
     * 字符串扩展函数判空,contract方式
     * @receiver String?  接收类型
     * @return Boolean    是否为空
     */
    @OptIn(ExperimentalContracts::class)
    fun String?.isNullOrEmptyWithContract(): Boolean {
        contract {
            returns(false) implies (this@isNullOrEmptyWithContract != null)
        }
        return this == null || this.isEmpty()
    }

    /**
     * 解决问题1 自定义函数判空后结果同步编译器
     * @param name String? 传入的姓名字符串
     */
    fun fixProblemNull(name: String?) {
        // 用contract方式的自定义扩展函数对局部变量判空
        if (!name.isNullOrEmptyWithContract()) {
            //运行正常
            // println("name:$name,length:${name.length}")
        }
    }

    fun main() {
//        runBlocking {
//            println("start")
//            launch {
//                println("async work started...")
//                delay(200)
//                println("async work done!")
//            }
//            println("end")
//        }
    }

}

