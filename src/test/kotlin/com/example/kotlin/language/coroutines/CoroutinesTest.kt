package com.example.kotlin.language.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class CoroutinesTest {
    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun getId(): String {
        return GlobalScope.async(Dispatchers.IO) {
            delay(1000)
            "hearing"
        }.await()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun getAvatar(id: String): String {
        return GlobalScope.async(Dispatchers.IO) {
            delay(1000)
            "avatar-$id"
        }.await()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun main(args: Array<String>) {
        GlobalScope.launch {
            val id = getId()
            val avatar = getAvatar(id)
            println("${Thread.currentThread().name} - $id - $avatar")
        }
    }

    /**
     * Coroutine builders are simple functions that create a new coroutine to run a given suspending function.
     * They can be called from normal non-suspending functions because they are not suspending themselves,
     * and thus act as a bridge between the normal and the suspending world.
     */

    @Test
    fun testCoroutines() {
        println("Hello,")

        // we create a coroutine running the provided suspending lambda
        // and block the main thread while waiting for the coroutine to finish its execution
        runBlocking {
            // now we are inside a coroutine
            delay(2000L) // suspends the current coroutine for 2 seconds
        }
        // will be executed after 2 seconds
        println("World!")
    }

    //Fire-and-forget with “launch”
    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testCoroutines1() {
        GlobalScope.launch { // launch new coroutine in background and continue
            delay(1000L)
            println("World!")
        }
        println("Hello,") // main thread continues here immediately
        runBlocking {     // but this expression blocks the main thread
            delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun testCoroutines2() {
        val deferredResult: Deferred<String> = GlobalScope.async {
            delay(1000L)
            "World!"
        }

        runBlocking {
            println("Hello, ${deferredResult.await()}")
        }
    }

}