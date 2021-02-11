package app.mobius

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

/**
 * PRECONDITION: Use package app.mobius so that Root WebApplicationContext will be initialized.
 * Same package of MobiusApplication.kt
 */
fun main(args: Array<String>) {
    runApplication<MobiusFeatureIntegrationTest>(*args)
}

@SpringBootTest(
        properties = [
            "spring.jpa.hibernate.ddl-auto=create-drop",
            "spring.liquibase.enabled=false",
            "spring.flyway.enabled=false"
        ]
)
@EnableAutoConfiguration
@ComponentScan
open class  MobiusFeatureIntegrationTest