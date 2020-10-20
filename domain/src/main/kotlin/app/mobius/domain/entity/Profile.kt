package app.mobius.domain.entity

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "profile")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Profile(
        @Id @GeneratedValue @Column(name = "profile_uuid") val profileUUID: UUID? = null,
        val name: String,
        val surname: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "location_uuid", referencedColumnName = "location_uuid")
        val location: Location,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "nationality_uuid", referencedColumnName = "country_uuid")
        val nationality: Country,

        val avatarUrl: String?,
        val nickname: String?,
        val biography: String?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "phone_uuid", referencedColumnName = "phone_uuid")
        val phone: Phone,

        val birthdate: Date,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val sex: Sex,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "gender_uuid", referencedColumnName = "gender_uuid", insertable=false, updatable = false)
        val gender: Gender? = null

) {
        constructor() : this(
                name = "",
                surname = "",
                location = Location(),
                nationality = Country(),
                avatarUrl = null,
                nickname = null,
                biography = null,
                phone = Phone(),
                birthdate = Date(),
                sex = Sex.F,
        )
}

@Entity
@Table(name = "phone")
data class Phone(
        @Id @GeneratedValue @Column(name = "phone_uuid") val phoneUUID: UUID? = null,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
) {
        constructor() : this(codeCountry = "", codeArea = "", number = -1)
}

enum class Sex {
    F, M
}