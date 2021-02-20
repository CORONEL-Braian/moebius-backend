package app.mobius.credentialManagment.data.repository

import app.mobius.credentialManagment.domain.entity.security.AppConsumer

interface AppConsumerRepository {
    fun findAllDevelopers() : List<AppConsumer.Developer>
}