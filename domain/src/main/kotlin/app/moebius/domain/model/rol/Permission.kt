package app.moebius.domain.model.rol

import java.util.*

data class Permission(
        val permissionUUID: UUID,
        val operation: Operation,
        val resource: Resource
)

enum class Operation {
    CREATE, READ, UPDATE, DELETE
}