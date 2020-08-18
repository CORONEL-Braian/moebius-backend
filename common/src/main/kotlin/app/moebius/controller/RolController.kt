package app.moebius.controller

import app.moebius.domain.model.rol.Rol
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RolController {

    @GetMapping("/roles")
    fun getRoles(): Rol = TODO()

    @GetMapping("/rol")
    fun editRol(rol: Rol): Rol = TODO()
}