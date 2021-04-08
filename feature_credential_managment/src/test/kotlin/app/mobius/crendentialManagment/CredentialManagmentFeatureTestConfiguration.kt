package app.mobius.crendentialManagment

import org.springframework.test.context.TestPropertySource

@Target(AnnotationTarget.CLASS)
@TestPropertySource(locations = ["/testPropertySource.properties"])
annotation class CredentialManagmentFeatureTestConfiguration