package app.moebius.controller

import app.moebius.domain.values.role.Role
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RolController {

    @GetMapping("/roles")
    fun getRoles(): Role = TODO()

    @GetMapping("/rol")
    fun editRol(role: Role): Role = TODO()
}