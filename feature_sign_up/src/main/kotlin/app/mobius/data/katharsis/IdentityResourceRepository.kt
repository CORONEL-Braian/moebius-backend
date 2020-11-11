package app.mobius.data.katharsis

import app.mobius.data.dao.PersonRepository
import app.mobius.domain.entity.Person
import io.katharsis.queryspec.QuerySpec
import io.katharsis.repository.ResourceRepositoryV2
import io.katharsis.resource.list.ResourceList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class IdentityResourceRepository: ResourceRepositoryV2<Person, String> {

    @Autowired
    private lateinit var personRepository: PersonRepository

    override fun getResourceClass(): Class<Person> {
        return Person::class.java
    }

    override fun findOne(id: String?, querySpec: QuerySpec?): Person? {
        id?.let {
            val person: Optional<Person> = personRepository.findById(id)
            return if (person.isPresent) {
                person.get()
            } else {
                null
            }
        }
        return null
    }

    override fun findAll(querySpec: QuerySpec?): ResourceList<Person>? {
        return querySpec?.apply(personRepository.findAll())
    }

    override fun findAll(ids: MutableIterable<String>?, querySpec: QuerySpec?): ResourceList<Person>? {
        return querySpec?.apply(personRepository.findAllById(ids))

    }

    override fun <S : Person?> save(entity: S): S {
        return personRepository.save(entity)
    }

    override fun <S : Person?> create(entity: S): S {
        return save(entity)
    }

    override fun delete(id: String?) {
        personRepository.deleteById(id)
    }
}