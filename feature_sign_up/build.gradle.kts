group = "app.mobius.backend.feature.signUp"
version = "0.1.0"

/**
 * Source: https://stackoverflow.com/a/57710092/5279996
 */
dependencies {
    implementation(project(":common-feature", "default"))
    implementation(project(":security-core", "default"))

//    Does not use in library_test_utils as runtimeOnly because test build by command fail
    testImplementation("org.springframework.security", "spring-security-test", "5.4.5")
}