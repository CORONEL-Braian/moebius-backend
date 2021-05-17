package app.mobius.signUp.domain

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(
        value = HttpStatus.NOT_ACCEPTABLE,
        reason = "Person is not acceptable"
)
class PersonNotRegisteredException : RuntimeException()