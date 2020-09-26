package app.mobius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@Component
@SpringBootApplication
open class MoebiusApplication

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}
