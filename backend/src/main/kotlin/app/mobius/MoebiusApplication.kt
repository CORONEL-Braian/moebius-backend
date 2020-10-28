package app.mobius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}

/**
 * PRE: A bean named 'entityManagerFactory' exists in the configuration for @EnableJpaRepositories
 */
@SpringBootApplication
open class MoebiusApplication : SpringBootServletInitializer()