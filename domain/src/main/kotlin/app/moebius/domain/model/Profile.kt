package app.moebius.domain.model

data class Profile(
        val profileId: Int,
        val dni: Int,
        val identificationNumber: Int,
        val name: String,
        val surname: String,
        val originLocality: String,
        val phone: Phone,
        val birthdate: Birthdate,
        val nationality: String,
        val sex: String,
        val avatarUrl: String,
        val isIdentityValidated: String,
)

data class Phone(
        val phoneId: Int,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

data class Birthdate(
        val birhdateId: Int,
        val day: Int,
        val month: Int,
        val year: Int
)