package com.example.kotlin.language.flow


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FlowTest {
    @Test
    fun testReduce() {
        println(listOf(1, 2, 3).reduce { a, b ->
            a + b
        }
        )


    }

    @Test
    fun testFold() {
        runBlocking {
            flowOf(1, 2, 3).runningFold("a") { a, b ->
                a + b
            }.collect {
                println(it)
            }
        }
    }

    @Test
    fun testEmit() = runBlocking {
        flow {
            emit(1)
            delay(590)
            emit(2)
            delay(590)
            emit(3)
            delay(1010)
            emit(4)
            delay(1010)
        }.debounce(
            1000
        ).collect {
            println(it)
        }
    }

    @Test
    fun testFlatMapMerge() = runBlocking {
        flowOf(1, 3).flatMapMerge {
            flowOf("$it a", "$it b")
        }.collect {
            println(it)
        }
    }

    @Test
    fun testFlatMapConcat() = runBlocking {
        flowOf(1, 3).flatMapConcat {
            flowOf("a", "b", "c")
        }.collect {
            println(it)
        }
    }

    @Test
    fun testBuffer() = runBlocking {
        flowOf("A", "B", "C", "D")
            .onEach {
                println("1 $it")
            }
            .collect { println("2 $it") }

        flowOf("A", "B", "C", "D")
            .onEach {
                println("1 $it")
            }
            .buffer()
            .collect { println("2 $it") }
    }

    @Test
    fun testCombine() = runBlocking {
        flowOf(1, 3).combine(
            flowOf("a", "b", "c")
        ) { a, b -> b + a }
            .collect {
                println(it)
            }
    }

    @Test
    fun testZip() = runBlocking {
        flowOf(1, 3).zip(
            flowOf("a", "b", "c")
        ) { a, b -> b + a }
            .collect {
                println(it)
            }
    }

    @Test
    fun testDistinctUntilChanged() = runBlocking {
        flowOf(1, 1, 2, 2, 3, 1).distinctUntilChanged().collect {
            println(it)
        }
    }
}
