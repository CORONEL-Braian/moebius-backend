package app.mobius.data

import app.mobius.domain.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * . AUTO IMPLEMENTED by Spring into a Bean called personRepository
 * . Communicates with the SQL data source
 */
@Repository
interface PersonRepository: CrudRepository<Person, Long>
