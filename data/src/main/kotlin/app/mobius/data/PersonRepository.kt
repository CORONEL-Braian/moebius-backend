package app.mobius.data

import app.mobius.domain.entity.Person

interface PersonRepository: CrudRepository<Person, Int>
