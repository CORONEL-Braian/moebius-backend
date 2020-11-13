package app.mobius.controller

import app.mobius.data.util.randomString
import app.mobius.domain.entity.Person
import app.mobius.domain.entity.Profile
import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import app.mobius.infrastructure.dto.PersonDto
import app.mobius.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * URL's start with /demo (after Application path)
 *
 * TODO
 * https://www.baeldung.com/json-api-java-spring-web-app
 */
@RestController
@RequestMapping("/people")
class PersonRestController {

    @Autowired
    private lateinit var personService: PersonService

    @PostMapping("/add")
    @ResponseBody
    fun addPerson(/*@RequestParam person: Person*/) : String  {
//        personRepository.save(person)
        return personService.createPerson(
                Person(
                        username = randomString(), profile = Profile(), setting = Setting(), role = Role()
                )
        )
    }
    /**
     * @return JSON or XML with the people
     */
    /*@GetMapping("/all")
    @ResponseBody
    fun getPeopleDto() : List<PersonDto> {
        val people = personService.getPeople()
        return people.map { convertFromEntityToDto(it) }
    }*/

    @GetMapping("/all2")
    @ResponseBody
    fun getPeopleResourceDto() : List<PersonDto>? {
        val people = personService.getPeopleResource()
        return people?.map { convertFromEntityToDto(it) }
    }

    /*@GetMapping("/person")
    fun getPerson(): Person = TODO()*/

    private fun convertFromEntityToDto(person: Person) : PersonDto {
        return PersonDto(
                personUUID = person.personUUID,
                username = person.username,
                profile = person.profile
        )
    }

    private fun convertFromDtoToEntity(personDto: PersonDto): Person? {
        var person: Person? = null
        if (personDto.personUUID != null) {
            person = personService.getPersonById()
        }
        return person
    }

}
