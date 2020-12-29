package app.mobius.data.repository

import app.mobius.domain.entity.security.AppConsumer

interface AppAuthorizationRepository {
//    fun isValidCredential(appConsumer: AppConsumer.AppConsumerPeople, privateKey: String) : Boolean
    fun isValidCredential() : Boolean
    fun isEntityManagerOpen(): Boolean
}