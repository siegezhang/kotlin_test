package com.example.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class KotlinTestApplication

fun main(args: Array<String>) {
    runApplication<KotlinTestApplication>(*args)
}

@Table("MESSAGES")
data class Message(@Id val id: String?, val text: String)


@RestController
class MessageResource(val service: MessageService) {
    @GetMapping
    fun index(): List<Message> = service.findMessages()


    @PostMapping
    fun post(@RequestBody message: Message) {
        service.post(message)
    }

}

interface MessageRepository : CrudRepository<Message, String> {

    @Query("select * from MESSAGES")
    fun findMessages(): List<Message>
}