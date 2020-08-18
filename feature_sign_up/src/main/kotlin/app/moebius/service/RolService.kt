package app.moebius.service

import org.springframework.stereotype.Service

interface RolService {
    fun getRol()
    fun editRol()
}

@Service
class BaseRolService : RolService {

    override fun getRol() {
        TODO("Not yet implemented")
    }

    override fun editRol() {
        TODO("Not yet implemented")
    }

}