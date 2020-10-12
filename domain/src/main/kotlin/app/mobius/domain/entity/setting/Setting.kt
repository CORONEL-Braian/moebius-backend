package app.mobius.domain.entity.setting

import app.mobius.domain.entity.setting.security.Security
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "setting")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Setting(
        @Id @GeneratedValue @Column(name = "settingUUID") val settingUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "accountUUID", referencedColumnName = "accountUUID")
        val account: Account,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "securityUUID", referencedColumnName = "securityUUID")
        val security: Security,

        @Enumerated(EnumType.STRING) @Column(name = "theme") @Type(type = "pgsql_enum")
        val theme: Theme = Theme.DEFAULT
) {
    constructor() : this(account = Account(), security = Security())
}

enum class Theme {
    LIGHT, DARK, DEFAULT
}