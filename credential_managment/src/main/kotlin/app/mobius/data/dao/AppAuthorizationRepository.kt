package app.mobius.data.dao

import app.mobius.domain.entity.security.AppConsumer

interface AppAuthorizationRepository {
    fun isValidCredential(appConsumer: AppConsumer.AppConsumerPeople? = null, privateKey: String? = null) : Boolean
}