package com.example.kotlin.language.kotlin18

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import java.nio.file.Paths
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.OnErrorResult
import kotlin.io.path.copyToRecursively

class Kotlin18Test {
    private val logger = KotlinLogging.logger {}

    @OptIn(ExperimentalPathApi::class)
    @Test
    fun test1() {
        val sourceRoot = Paths.get("./createParentDirectories")
        val destinationRoot = Paths.get("./createParentDirectories1")
        sourceRoot.copyToRecursively(destinationRoot, followLinks = false,
            onError = { source, target, exception ->
                OnErrorResult.TERMINATE
            })
    }
}