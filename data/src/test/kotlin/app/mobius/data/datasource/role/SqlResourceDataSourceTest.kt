package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Resource
import org.junit.jupiter.api.Test

class SqlResourceDataSourceTest {

    @Test
    fun `create a resource`() {
        val randomLocation = randomString("/test")
        val resource = Resource(null, "test", randomLocation)

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(resource)
        }
    }

}