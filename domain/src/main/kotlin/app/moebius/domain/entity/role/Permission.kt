package app.moebius.domain.entity.role

import java.util.*

data class Permission(
        val permissionUUID: UUID,
        val operation: Operation,
        val resource: Resource
)

enum class Operation {
    CREATE, READ, UPDATE, DELETE
}