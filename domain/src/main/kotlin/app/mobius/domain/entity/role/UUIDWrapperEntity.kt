package app.mobius.domain.entity.role

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class UUIDWrapperEntity(
        @Id
        @Column(name="UUID")
        private val uuid: UUID? = null

)