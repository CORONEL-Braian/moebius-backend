package app.moebius.domain.entity.role

import org.hibernate.HibernateException
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.EnumType
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Types


class PostgreSQLEnumType2 : EnumType<Enum<*>>() {
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
