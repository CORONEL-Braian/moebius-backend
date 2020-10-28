package app.mobius.domain.entity

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.*
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "profile")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
@DynamicInsert
data class Profile(
        @Id @GeneratedValue @Column(name = "profile_uuid") val profileUUID: UUID? = null,
        var name: String,
        var surname: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "location_uuid", referencedColumnName = "location_uuid")
        var location: Location,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "nationality_uuid", referencedColumnName = "country_uuid")
        var nationality: Country,

        var avatarUrl: String?,
        var nickname: String?,
        var biography: String?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "phone_uuid", referencedColumnName = "phone_uuid")
        var phone: Phone,

        var birthdate: Date,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        var sex: Sex,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "gender_uuid", referencedColumnName = "gender_uuid")
        var gender: Gender? = null

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
        var codeCountry: String,
        var codeArea: String,
        var number: Int
) {
        constructor() : this(codeCountry = "", codeArea = "", number = -1)
}

enum class Sex {
    F, M
}

