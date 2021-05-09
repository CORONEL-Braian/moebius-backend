package app.mobius.signUp.data.repository

import app.mobius.domain.entity.profile.Profile

interface ProfileRepository {
    fun findAllProfiles(): List<Profile>
    fun isEntityManagerOpen(): Boolean
}