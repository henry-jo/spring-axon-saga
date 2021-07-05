package com.henry.order

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class OrderApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(OrderApiApplication::class.java, *args)
}
