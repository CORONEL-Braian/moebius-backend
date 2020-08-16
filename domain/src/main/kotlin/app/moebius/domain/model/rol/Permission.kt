package app.moebius.domain.model.rol

data class Permission(
        val permissionId: Int,
        val operation: Operation,
        val resource: Resource
)

enum class Operation {
    CREATE, READ, UPDATE, DELETE
}