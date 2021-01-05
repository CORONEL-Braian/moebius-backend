package app.mobius.data.repository

import app.mobius.domain.entity.security.AppConsumer

interface AppConsumerRepository {
    fun findAllDevelopers() : List<AppConsumer.Developer>
}