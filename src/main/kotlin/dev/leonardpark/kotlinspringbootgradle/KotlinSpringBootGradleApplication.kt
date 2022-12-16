package dev.leonardpark.kotlinspringbootgradle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootGradleApplication

fun main(args: Array<String>) {
  runApplication<KotlinSpringBootGradleApplication>(*args)
}
