package app.mobius.controller

import app.mobius.data.util.randomString
import app.mobius.domain.entity.Person
import app.mobius.domain.entity.Profile
import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import app.mobius.infrastructure.dto.PersonDto
import app.mobius.infrastructure.model.request.JsonApiRequest
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

    /**
     * PRE: Composite classes in JsonApiRequest have a default constructor
     * OBS: Replace JsonApiRequest with Any for debugging
     *
     * jsonapi-sign-up.json -> JsonApiRequest -> PersonDTO
     */
    @PostMapping("/add")
    @ResponseBody
    fun addPerson(@RequestBody person: JsonApiRequest) : String  {

//        TODO: Transform from JsonApiRequest to PersonDTO.

        return personService.createPerson(
                Person(
                        username = randomString(), profile = Profile(), setting = Setting(), role = Role()
                )
        )
    }
    /**
     * @return JSON or XML with the people
     */
    @GetMapping("/all")
    @ResponseBody
    fun getPeople() : List<Person> {
        return personService.getPeople()
    }

    /**
     * @return JSON or XML with the people
     */
    @GetMapping("/allDTO")
    @ResponseBody
    fun getPeopleDto() : List<PersonDto> {
        val people = personService.getPeople()
        return people.map { convertFromEntityToDto(it) }
    }

    /*@GetMapping("/person")
    fun getPerson(): Person = TODO()*/

    private fun convertFromEntityToDto(person: Person) : PersonDto {
        return PersonDto(
                username = person.username,
                /*profile = person.profile,
                setting = person.setting*/
        )
    }

    private fun convertFromDtoToEntity(personDto: PersonDto): Person {
        var person: Person = Person()
//               TODO:

        return person
    }

}
