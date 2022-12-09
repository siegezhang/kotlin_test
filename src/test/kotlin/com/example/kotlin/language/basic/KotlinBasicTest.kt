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
            in 0.0..<0.25 -> 1.0 // First quarter
            in 0.25..<0.5 -> 1 // Second quarter
            in 0.5..<0.75 -> 3 // Third quarter
            in 0.75..1.0 -> 4  // Last quarter  <- Note closed range here
        }
    }

    @Test
    fun testUnderscoreOperator() {
        // T is inferred as String because SomeImplementation derives from SomeClass<String>
        val s = Runner.run<SomeImplementation, _>()
        assert(s == "Test")

        // T is inferred as Int because OtherImplementation derives from SomeClass<Int>
        val n = Runner.run<OtherImplementation, _>()
        assert(n == 42)
    }

    @Test
    fun testVararg() {
        compute()
        compute("leavesC")
        compute("leavesC", "leavesc")
        compute("leavesC", "leavesc", "叶")
        val names = arrayOf("leavesC", "leavesc", "叶")
        compute(* names)
    }

    @Test
    fun testNestFun() {
        compute("leavesC", "country")
    }

    @Test
    fun testIf() {
        println(getLength(null))

        val maxValue = if (20 > 10) {
            println("maxValue is 20")
            20
        } else {
            println("maxValue is 10")
            10
        }
    }

    @Test
    fun testIf1() {
        val list = listOf(1, 4, 10, 4, 10, 30)
        val value = if (list.size > 0) list.size else null
        println(value)  //6
        val value1 = if (list.size > 0) println("1") else println("2")
        println(value1.javaClass)   //class kotlin.Unit
    }

    /**
     * when 表达式与 Java 中的 switch/case 类似，但是要强大得多。when 既可以被当做表达式使用也可以被当做语句使用，
     * when 将参数和所有的分支条件顺序比较直到某个分支满足条件，然后它会运行右边的表达式。
     * 如果 when 被当做表达式来使用，符合条件的分支的值就是整个表达式的值，如果当做语句使用，
     * 则忽略个别分支的值。与 Java 的 switch/case 不同之处是 when 表达式的参数可以是任何类型，并且分支也可以是一个条件
     */
    @Test
    fun testWhen() {
        val value = 2
        when (value) {
            in 4..9 -> println("in 4..9") //区间判断
            3 -> println("value is 3")    //相等性判断
            2, 6 -> println("value is 2 or 6")    //多值相等性判断
            is Int -> println("is Int")   //类型判断
            else -> println("else")       //如果以上条件都不满足，则执行 else
        }

        //返回 when 表达式
        fun parser(obj: Any): String =
            when (obj) {
                1 -> "value is 1"
                "4" -> "value is string 4"
                is Long -> "value type is long"
                else -> "unknown"
            }
        println(parser(1))
        println(parser(1L))
        println(parser("4"))
        println(parser(100L))
        println(parser(100.0))
        //when 语句也可以不带参数来使用

        when {
            1 > 5 -> println("1 > 5")
            3 > 1 -> println("3 > 1")
        }
    }

    @Test
    fun testIndex() {
        val items = listOf("H", "e", "l", "l", "o")
        //通过索引来遍历List
        for (index in items.indices) {
            println("${index}对应的值是：${items[index]}")
        }

        val list = listOf(1, 4, 10, 34, 10)
        for ((index, value) in list.withIndex()) {
            println("index : $index , value :$value")
        }

    }

    @Test
    fun testForEachReturn() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return
            }
            println("value is $it")
        }
        println("function end")
    }

    /**
     * 标签限制的 return 允许我们从外层函数返回，最重要的一个用途就是从 lambda 表达式中返回。
     * 通常情况下使用隐式标签更方便，该标签与接受该 lambda 的函数同名
     */
    @Test
    fun testForEachReturn1() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return@testForEachReturn1
            }
            println("value is $it")
        }
        println("function end")

    }

    @Test
    fun testForEachReturn2() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach {
            if (it == 8) {
                return@forEach
            }
            println("value is $it")
        }
        println("function end")
    }

    @Test
    fun testForEachReturn3() {
        val list = listOf(1, 4, 6, 8, 12, 23, 40)
        list.forEach loop@{
            if (it == 8) {
                return@loop
            }
            println("value is $it")
        }
        println("function end")
    }

    @Test
    fun testForEachReturn4() {
        listOf(1, 2, 3, 4, 5).forEach(fun(value: Int) {
            if (value == 3) {
                //局部返回到匿名函数的调用者，即 forEach 循环
                return
            }
            println("value is $value")
        })
        println("function end")
    }

    @Test
    fun testElvis() {
        fun check(name: String?) {
            println(name ?: "default")
        }
        println(check(null))
    }

    @Test
    fun testAs() {
        fun check(any: Any?) {
            val result = any as? String
            println(result ?: println("is not String"))
        }
        check(null)
    }

    /**
     * 非空断言用于把任何值转换为非空类型，如果对 null 值做非空断言，则会抛出异常
     */
    @Test
    fun testDoubleNot() {
        fun check(name: String?) {
            println(name!!.length)
        }

        var name: String? = "leavesC"
        check(name) //7

        name = null
        check(name) //kotlinNullPointerException
    }

    /**
     *为可空类型定义扩展函数是一种更强大的处理 null 值的方式，可以允许接收者为 null 的调用，
     * 并在该函数中处理 null ，而不是在确保变量不为 null 之后再调用它的方法
     *
     * isNullOrEmpty() 的方法签名如下所示，可以看到这是为可空类型 CharSequence? 定义的扩展函数，方法中已经处理了方法调用者为 null 的情况
     * <code>
     * @kotlin.internal.InlineOnly
     * public inline fun CharSequence?.isNullOrEmpty(): Boolean {
     *     contract {
     *         returns(false) implies (this@isNullOrEmpty != null)
     *     }
     *     return this == null || this.length == 0
     * }
     *</code>
     */
    @Test
    fun testEmptyExtension() {
        val name: String? = null
        println(name.isNullOrEmpty()) //true
    }

    //与 Java 不同，kotlin 中的 if 是作为表达式存在的，其可以拥有返回值
    //完全可以用来替代 Java 中的三元运算符，因此 kotlin 并没有三元运算符
    //如果 if 表达式分支是用于执行某个命令，那么此时的返回值类型就是 Unit ，此时的 if 语句就看起来和 Java 的一样了
    fun getLength(str: String?): Int {
        return if (str.isNullOrBlank()) 0 else str.length
    }


    fun compute(name: String, country: String) {
        fun check(string: String) {
            if (string.isEmpty()) {
                throw IllegalArgumentException("参数错误")
            }
        }
        check(name)
        check(country)
    }


    fun compute(vararg name: String) {
        name.forEach { println(it) }
    }

    abstract class SomeClass<T> {
        abstract fun execute(): T
    }

    class SomeImplementation : SomeClass<String>() {
        override fun execute(): String = "Test"
    }

    class OtherImplementation : SomeClass<Int>() {
        override fun execute(): Int = 42
    }

    object Runner {
        inline fun <reified S : SomeClass<T>, T> run(): T {
            return S::class.java.getDeclaredConstructor().newInstance().execute()
        }
    }
}