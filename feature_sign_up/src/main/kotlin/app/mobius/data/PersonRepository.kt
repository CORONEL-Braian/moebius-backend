package app.mobius.data

import app.mobius.domain.entity.Person
import org.springframework.data.repository.CrudRepository

/**
 * OBS: This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
 */
interface PersonRepository: CrudRepository<Person, Int>