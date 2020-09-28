package app.mobius.data.di

import org.springframework.boot.autoconfigure.domain.EntityScan
import javax.persistence.Id
import javax.persistence.Table

@EntityScan
@Table(name = "test")
data class SomeTest(@Id val test: String?)