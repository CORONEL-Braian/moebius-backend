package app.mobius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}

/**
 * PRE: A bean named 'entityManagerFactory' exists in the configuration for @EnableJpaRepositories
 */
@SpringBootApplication(scanBasePackages = ["app.mobius"])
@EnableJpaRepositories(basePackages = ["app.mobius.domain.entity", "app.mobius"])
@EnableTransactionManagement
open class MoebiusApplication : SpringBootServletInitializer()