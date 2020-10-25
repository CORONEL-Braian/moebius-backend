package app.mobius

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping/*(path = ["/demo"])*/
class TestController {

    @GetMapping(path = ["/test2"])
    fun testPerson() = "Test"

}
