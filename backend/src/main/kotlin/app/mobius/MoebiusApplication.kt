package app.mobius

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Component

@SpringBootApplication()
//@SpringBootApplication(scanBasePackages = ["app.mobius"])
@EnableJpaRepositories("app.mobius.data")   // Define access to db
open class MoebiusApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
    runApplication<MoebiusApplication>(*args)
}