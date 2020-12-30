package app.mobius.data.repository

import app.mobius.domain.entity.security.AppAuthorization
import app.mobius.domain.entity.security.AppConsumer

interface AppAuthorizationRepository {
    fun isValidAppAuthorization(appConsumer: AppConsumer.Developer, privateKey: String) : Boolean
    fun isEntityManagerOpen(): Boolean
    fun findAllDevelopers() : List<AppAuthorization.Developer>
}