package app.moebius.domain.entity

import javax.persistence.Entity

@Entity
data class Profile(
        val profileId: Int,
        val dni: Int,
        val identificationNumber: Int,
        val name: String,
        val surname: String,
        val facebookId: Int,
        val originLocality: String,
        val phone: Phone,
        val birthdate: Birthdate,
        val nationality: String,
        val sex: String,
        val avatarUrl: String,
        val isIdentityValidated: String,
)

@Entity
data class Phone(
        val country: String,
        val codeArea: String,
        val number: Int
)

@Entity
data class Birthdate(
        val day: Int,
        val month: Int,
        val year: Int
)