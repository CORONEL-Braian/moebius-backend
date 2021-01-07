package app.mobius

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}

/**
 * PRE: A bean named 'entityManagerFactory' exists in the configuration for @Configuration
 */
@SpringBootApplication
@SpringBootConfiguration
open class MoebiusApplication : SpringBootServletInitializer()