package ru.otus.userprofile

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserProfileApplication

fun main(args: Array<String>) {
    runApplication<UserProfileApplication>(*args)
}
