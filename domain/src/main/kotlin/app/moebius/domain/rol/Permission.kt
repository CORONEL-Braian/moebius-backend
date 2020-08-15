package app.moebius.domain.rol

data class Permission(
        val permissionId: Int,
        val operation: Operation,
        val resource: Resource
)

enum class Operation {
    CREATE, READ, UPDATE, DELETE
}