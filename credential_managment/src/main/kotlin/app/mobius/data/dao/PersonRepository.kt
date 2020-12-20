package app.mobius.data.dao

import app.mobius.domain.entity.AppCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * . AUTO IMPLEMENTED by Spring into a Bean called personRepository
 * . Communicates with the SQL data source
 */
@Repository
interface AppCredentialsRepository: JpaRepository<AppCredentials, String>