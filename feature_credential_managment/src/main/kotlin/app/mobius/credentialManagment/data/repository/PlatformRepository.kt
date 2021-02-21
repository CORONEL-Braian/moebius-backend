package app.mobius.credentialManagment.data.repository

import app.mobius.credentialManagment.domain.entity.security.Platform

interface PlatformRepository {
    fun findAndroidPlatforms() : List<Platform>
}