package com.example.kotlin.language.kotlin19

import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.copyToRecursively
import kotlin.io.path.createParentDirectories

class Kotlin19Test {
    //Stable ..< operator for open-ended ranges
    @Test
    fun test() {
        for (number in 2..<10) {
            if (number % 2 == 0) {
                print("$number ")
            }
        }
    }

    //New common function to get regex capture group by name
    @Test
    fun testReg() {
        val regex = """\b(?<city>[A-Za-z\s]+),\s(?<state>[A-Z]{2}):\s(?<areaCode>[0-9]{3})\b""".toRegex()
        val input = "Coordinates: Austin, TX: 123"
        val match = regex.find(input)!!
        println(match.groups["city"]?.value)
        println(match.groups["state"]?.value)
        println(match.groups["areaCode"]?.value)
    }

    //New path utility to create parent directories
    @OptIn(ExperimentalPathApi::class)
    @Test
    fun testPath() {
        val sourcePath = Paths.get("./createParentDirectories")
        val destinationPath = Paths.get("./createParentDirectories1")
        sourcePath.copyToRecursively(
            destinationPath.createParentDirectories(),
            followLinks = false
        )

    }
}