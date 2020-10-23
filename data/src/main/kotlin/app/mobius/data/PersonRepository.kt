package app.mobius.data

import app.mobius.domain.entity.Person
import org.springframework.data.repository.CrudRepository

interface PersonRepository: CrudRepository<Person, Int>
