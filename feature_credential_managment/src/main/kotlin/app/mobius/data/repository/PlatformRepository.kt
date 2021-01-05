package app.mobius.data.repository

import app.mobius.domain.entity.security.Platform

interface PlatformRepository {
    fun findAndroidPlatforms() : List<Platform>
}