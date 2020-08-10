package app.moebius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class MoebiusApplication

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}