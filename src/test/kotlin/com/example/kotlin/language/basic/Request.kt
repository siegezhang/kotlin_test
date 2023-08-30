package com.example.kotlin.language.basic

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

data class Request(val arg: String)

@ExperimentalContracts
class Service {
    fun process(request: Request?) {
        validate(request)
        println(request.arg) // Doesn't compile because request might be null
    }
}

@ExperimentalContracts
private fun validate(request: Request?) {
    contract {
        returns() implies (request != null)
    }
    if (request == null) {
        throw IllegalArgumentException("Undefined request")
    }
    if (request.arg.isBlank()) {
        throw IllegalArgumentException("No argument is provided")
    }
}

//Making Guarantees Based on the Return Value
data class MyEvent(val message: String)

@ExperimentalContracts
fun processEvent(event: Any?) {
    if (isInterested(event)) {
        println(event.message)
    }
}

@ExperimentalContracts
fun isInterested(event: Any?): Boolean {
    contract {
        returns(true) implies (event is MyEvent)
    }
    return event is MyEvent
}

//Making Guarantees About a Function’s Usage
@ExperimentalContracts
inline fun <R> myRun(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

@ExperimentalContracts
fun callsInPlace() {
    val i: Int
    myRun {
        i = 1 // Is forbidden due to possible re-assignment
    }
    println(i) // Is forbidden because the variable might be uninitialized
}