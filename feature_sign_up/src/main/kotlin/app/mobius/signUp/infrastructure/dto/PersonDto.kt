package app.mobius.signUp.infrastructure.dto

import app.mobius.domain.entity.Profile
import app.mobius.domain.entity.setting.Setting
import java.util.*
import javax.persistence.*

data class PersonDto(
        val username: String,
        /*val profile: Profile,
        val setting: Setting,*/
) {
    constructor() : this(username = ""/*, profile = Profile(), setting = Setting()*/)
}