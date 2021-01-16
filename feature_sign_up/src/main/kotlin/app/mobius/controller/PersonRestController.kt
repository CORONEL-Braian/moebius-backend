package app.mobius.controller

import app.mobius.data.util.randomString
import app.mobius.domain.entity.Person
import app.mobius.domain.entity.Profile
import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import app.mobius.infrastructure.dto.PersonDto
import app.mobius.infrastructure.model.request.JsonApiRequest
import app.mobius.service.PersonService
import app.mobius.service.ProfileService
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

    @Autowired
    private lateinit var profileService: ProfileService

    /**
     * PRE: Composite classes in JsonApiRequest have a default constructor
     * OBS: For debugging, replace data type of @param JsonApiRequest with Any
     *
     * signUpJsonApi.json -> JsonApiRequest -> PersonDTO
     */
    @PostMapping("/add")
    @ResponseBody
    fun addPerson(
            @RequestHeader("MOBIUS-KMM-authorization") mobiusKmmAuthorizationDeveloperKey: String,
            @RequestHeader("MOBIUS-KMM-platform-name") platformName: String,
            @RequestHeader("MOBIUS-KMM-platform-ecosystem") platformEcosystem: String,

//            @RequestBody person: JsonApiRequest   TODO: Enable
            @RequestBody person: Any
    ) : String  {

//        TODO: Transform from JsonApiRequest to PersonDTO.
//        TODO: Transform PersonDTO to Person

    return personService.createPerson(
            Person(
                    username = randomString(), profile = Profile(), setting = Setting(), role = Role()
            )
    )
    }

    @GetMapping("/profile/all")
    @ResponseBody
    fun getProfiles() : List<Profile> {
        return profileService.getProfiles()
    }

    @GetMapping("/profile/isOpen")
    @ResponseBody
    fun isOpen() : Boolean {
        return profileService.isOpen()
    }

    /**
     * @return JSON or XML with the people
     */
    @GetMapping("/all")
    @ResponseBody
    fun getPeople() : List<PersonDto> {
        val people = personService.getPeople()
//        TODO: Return transform from PersonDTO to JsonApiRequest
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

//     TODO: Return T
    private  fun <T> convertJsonApiRequestToDTO(jsonApiRequest: JsonApiRequest, instance: T) : Any {
        var username = ""
        jsonApiRequest.data.map {
            username = it.attributes["username"] as String
        }
        return PersonDto(
                username = username
        )
    }

}
