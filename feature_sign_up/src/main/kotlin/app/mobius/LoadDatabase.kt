package app.mobius

import app.mobius.domain.entity.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


/*
@Configuration
@EnableAutoConfiguration
@ComponentScan
open class LoadDatabase {

    @Bean
    open fun initDatabase2(personRepository: PersonRepository): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            log.info("Preloading " + personRepository.save(Person()))
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(LoadDatabase::class.java)
    }
}*/
