package app.mobius.signUp.controller

import app.mobius.domain.entity.profile.Profile
import app.mobius.jsonApi.model.JsonApiResource
import app.mobius.signUp.domain.PersonNotRegisteredException
import app.mobius.signUp.infrastructure.dto.PersonDto
import app.mobius.signUp.service.ProfileService
import app.mobius.signUp.service.SignUpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kotlin.io.path.ExperimentalPathApi

/**
 * URL's start with /people (after Application path)
 */
@ExperimentalPathApi
@RestController
@RequestMapping("/people")
class SignUpRestController {

    @Autowired
    private lateinit var signUpService: SignUpService

    @Autowired
    private lateinit var profileService: ProfileService

    @GetMapping("/status")
    @ResponseBody
    fun featureStatus() : String {
        return if (profileService.isOpen()) {
            "No problem"
        } else {
            "Service interruption"
        }
    }

    /**
     * PRE: Composite classes in JsonApiRequest have a default constructor
     * OBS: For debugging, replace data type of @param JsonApiRequest with Any
     *
     * signUpJsonApi.json -> JsonApiRequest -> PersonDTO
     */
    @PostMapping("/add")
    @ResponseBody
    fun addPerson(
            @RequestBody personResource: JsonApiResource,
    ) : String  {
        return try {
            signUpService.createPerson(personResource)
        } catch (e: Exception) {
            throw PersonNotRegisteredException()
        }
    }

    @GetMapping("/profile/all")
    @ResponseBody
    fun getProfiles() : List<Profile> {
        return profileService.getProfiles()
    }

    /**
     * @return JSON or XML with the people
     */
    @GetMapping("/all")
    @ResponseBody
    fun getPeople() : List<PersonDto> {
        val people = signUpService.getPeople()
//        TODO: Return transform from PersonDTO to JsonApiRequest
        return people.map { signUpService.convertFromEntityToDto(it) }
    }

    /*@GetMapping("/person")
    fun getPerson(): Person = TODO()*/

}
