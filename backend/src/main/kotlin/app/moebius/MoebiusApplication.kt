package app.moebius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.stereotype.Component

@Component
@SpringBootApplication
open class MoebiusApplication: SpringBootServletInitializer()



fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}