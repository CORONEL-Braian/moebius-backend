package app.mobius

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

fun main(args: Array<String>) {
    runApplication<MobiusApplicationTest>(*args)
}

/**
 * TODO: Should exludeFilters in @ComponentScan as in @SpringBootApplication?
 */
@SpringBootTest(
        properties = [
            "spring.jpa.hibernate.ddl-auto=create-drop",
            "spring.liquibase.enabled=false",
            "spring.flyway.enabled=false"
        ]
)
@EnableAutoConfiguration
@ComponentScan
open class MobiusApplicationTest