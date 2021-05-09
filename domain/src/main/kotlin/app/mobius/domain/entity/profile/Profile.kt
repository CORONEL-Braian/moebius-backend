package app.mobius.domain.entity.profile

import app.mobius.domain.entity.Country
import app.mobius.domain.entity.Gender
import app.mobius.domain.entity.Location
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.*
import java.sql.Time
import java.sql.Timestamp
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

        @Temporal(TemporalType.DATE)
        @Column(name = "birth_date")
        var birthDate: Date,

        @Temporal(TemporalType.TIME)
        @Column(name = "birth_time")
        var birthTime: Date,

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
                birthDate = Date(),
                birthTime = Date(),
                sex = Sex.F,
        )
}