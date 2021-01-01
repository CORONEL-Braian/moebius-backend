package app.mobius.data.repository

import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer
import app.mobius.domain.entity.security.Platform
import org.springframework.data.jpa.repository.Query

interface AppAuthorizationRepository {
    fun isValidAppAuthorization(appConsumer: AppConsumer.Developer, privateKey: String) : Boolean
    fun isEntityManagerOpen(): Boolean
    fun findAllDevelopers() : List<AppAuthorization.Developer>
    fun findAppAuthorizationDeveloperUUID() : String
}