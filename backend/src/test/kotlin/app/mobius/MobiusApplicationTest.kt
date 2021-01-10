package app.mobius

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

fun main(args: Array<String>) {
    runApplication<MobiusApplicationTest>(*args)
}

@EnableAutoConfiguration
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [
    MobiusApplication::class
])
open class MobiusApplicationTest

