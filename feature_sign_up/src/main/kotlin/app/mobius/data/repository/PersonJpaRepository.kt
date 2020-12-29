package app.mobius.data.repository

import app.mobius.domain.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * . AUTO IMPLEMENTED by Spring into a Bean called personRepository
 * . Communicates with the SQL data source
 */
@Repository
interface PersonJpaRepository: JpaRepository<Person, String>