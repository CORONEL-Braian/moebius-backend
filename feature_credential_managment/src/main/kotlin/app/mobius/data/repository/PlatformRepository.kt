package app.mobius.data.repository

import app.mobius.domain.entity.security.Platform
import org.springframework.stereotype.Component

interface PlatformRepository {
    fun findAndroidPlatforms() : List<Platform>
}