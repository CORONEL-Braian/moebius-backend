package app.moebius.domain.entity.role

import org.hibernate.HibernateException
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.engine.spi.SharedSessionContractImplementor
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Types
import java.util.*
import javax.persistence.*


/*@Entity
@Table(name = "subscription")*/
/*@TypeDef(
        name = "pgsql_enum",
        typeClass = StatusSubscription::class.java
        )*/
//data class Subscription(

      /*  @Id @GeneratedValue @Column(name = "subscription_uuid", columnDefinition = "uuid", updatable = false)
        val subscriptionUUID: UUID,
*/
        /*@field:Enumerated(EnumType.STRING) @field:Column(name = "test")
        val statusSubscription: StatusSubscription = StatusSubscription.PREMIUM*/

        /*@field:MapKeyEnumerated(EnumType.STRING) @field:Column(name = "test2")
        val test: StatusSubscription = StatusSubscription.FREE*/

/*
        @field:Enumerated(EnumType.STRING) @field:Column(name = "test2") @Type(type = "status_subscription")
        val test: StatusSubscription = StatusSubscription.FREE

) {

}

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}

class PostgreSQLEnumType2 {

    @Throws(HibernateException::class, SQLException::class)
    fun <T: Enum<*>> Class<T>.nullSafeSet(
            st:PreparedStatement,
            value:Any,
            index:Int,
            session:SharedSessionContractImplementor) {
        st.setObject(
                index,
                value.toString(),
                Types.OTHER
        )
    }
}
*/

