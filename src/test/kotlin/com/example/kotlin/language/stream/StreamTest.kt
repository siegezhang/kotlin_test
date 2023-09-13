package com.example.kotlin.language.stream;

import org.junit.jupiter.api.Test;

class StreamTest {
    @Test
    fun testMapIndexed() {
        val numbers = setOf(1, 2, 3)
        println(numbers.map { it * 3 })
        println(numbers.mapIndexed { idx, value -> value * idx })
    }

    @Test
    fun testMapNotNull() {
        val strings: List<String> = listOf("12a", "45", "", "3")
        val ints: List<Int> = strings.mapNotNull { it.toIntOrNull() }

        println(ints) // [45, 3]
        println(ints.sum()) // 48
    }

    @Test
    fun testFilterIndexed() {
        val numbers: List<Int> = listOf(0, 1, 2, 3, 4, 8, 6)
        val numbersOnSameIndexAsValue = numbers.filterIndexed { index, i -> index == i }
        println(numbersOnSameIndexAsValue) // [0, 1, 2, 3, 4, 6]
    }

    @Test
    fun testFilterNotNull() {
        val numbers: List<Int?> = listOf(1, 2, null, 4)
        val nonNullNumbers = numbers.filterNotNull()

        println(nonNullNumbers) // [1, 2, 4]
    }

    @Test
    fun testOnEachIndexed() {
        val list = mutableListOf("a", "b", "c", "d").onEachIndexed { index, item ->
            println(index.toString() + ":" + item)
        }

    }

    @Test
    fun testDistinctBy() {
        val list = listOf('a', 'A', 'b', 'B', 'A', 'a')
        println(list.distinct()) // [a, A, b, B]
        println(list.distinctBy { it.uppercaseChar() }) // [a, b]
    }

    @Test
    fun testSortedBy() {
        val list = listOf("aaa", "cc", "bbbb")
        val sorted = list.sortedBy { it.length }

        println(list) // [aaa, cc, bbbb]
        println(sorted) // [cc, aaa, bbbb]
    }

    @Test
    fun testSortedWith() {
        class Person(val firstName: String, val lastName: String) {
            override fun toString(): String = "$firstName $lastName"
        }

        val people = mutableListOf(
            Person("Ragnar", "Lodbrok"),
            Person("Bjorn", "Ironside"),
            Person("Sweyn", "Forkbeard")
        )
        people.sortWith(compareByDescending { it.firstName })
        // after sorting
        println(people.joinToString()) // Sweyn Forkbeard, Ragnar Lodbrok, Bjorn Ironside
    }

    @Test
    fun testZip() {
        val stringA = "abcd"
        val stringB = "zyx"
        println(stringA zip stringB) // [(a, z), (b, y), (c, x)]
    }

    @Test
    fun testZip1() {
        val stringA = "abcd"
        val stringB = "zyx"
        val result = stringA.zip(stringB) { a, b -> "$a$b" }
        println(result) // [az, by, cx]
    }

    @Test
    fun testZip2() {
        val listA = listOf("a", "b", "c")
        val listB = listOf(1, 2, 3, 4)
        println(listA zip listB) // [(a, 1), (b, 2), (c, 3)]
    }

    @Test
    fun testZip3() {
        val listA = listOf("a", "b", "c")
        val listB = listOf(1, 2, 3, 4)
        val result = listA.zip(listB) { a, b -> "$a$b" }
        println(result) // [a1, b2, c3]
    }

    @Test
    fun testZipWithNext() {
        val letters = ('a'..'f').toList()
        val pairs = letters.zipWithNext()

        println(letters) // [a, b, c, d, e, f]
        println(pairs) // [(a, b), (b, c), (c, d), (d, e), (e, f)]
    }

    @Test
    fun testZipWithNext1() {
        val values = listOf(1, 4, 9, 16, 25, 36)
        val deltas = values.zipWithNext { a, b -> b - a }
        println(deltas) // [3, 5, 7, 9, 11]
    }
}



