package app.moebius.domain.util

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.EnumType
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Types

/**
 * Source: https://stackoverflow.com/a/64021041/5279996
 */
class PostgreSQLEnumType : EnumType<Enum<*>>() {

    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeSet(
            st: PreparedStatement,
            value: Any,
            index: Int,
            session: SharedSessionContractImplementor) {
        st.setObject(
                index,
                value.toString(),
                Types.OTHER
        )
    }

}
