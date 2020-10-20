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

//        @Generated(GenerationTime.INSERT)
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "gender_uuid", referencedColumnName = "gender_uuid")
        @MapsId
        val gender: Gender

) {
        /**
         * TODO:
         * Insert default value for sub-entity
         * Insert some existing value
         */
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
                gender = Gender()
//                gender = Gender(genderUUID = UUID.fromString("c87ee95b-06f1-52ab-83ed-5d882ae400e6"), type = "")
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