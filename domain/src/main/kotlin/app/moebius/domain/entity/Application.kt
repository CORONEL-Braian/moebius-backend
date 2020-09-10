package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "application")
data class Application(
        val applicationUUID: UUID,
        val name: String,
        val hashPassword: String
)