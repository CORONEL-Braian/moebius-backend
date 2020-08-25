package app.moebius.domain.entity.rol

data class Permission(
        val permissionUUID: Int,
        val operation: Operation,
        val resource: Resource
)

enum class Operation {
    CREATE, READ, UPDATE, DELETE
}