//apply(from = "${rootProject.projectDir}/common-feature-test-utils.gradle.kts")

group = "app.möbius"
version = "0.0.0"


/**
 * Precondition: Run tests using Intellij IDEA in
 *  Settings > Build, Execution, Deployment > Build Tools > Gradle
 * Observation: To inherit dependencies do not use the test prefix
 * Source:
 *  https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
 */
dependencies {
    api("org.springframework.boot", "spring-boot-starter-test", "2.4.1")

    runtimeOnly("io.mockk:mockk:1.10.0")
    runtimeOnly("org.junit.jupiter", "junit-jupiter-api", "5.6.2")
    runtimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.2")
    runtimeOnly("org.mockito", "mockito-junit-jupiter", "3.2.4")
    runtimeOnly("org.springframework.boot", "spring-boot-starter-test", "2.4.1")
    runtimeOnly("org.springframework.security", "spring-security-test", "5.4.2")
}

/**
 * TODO: Delete task in all submodules. For check this: build and run tests using Gradle in Build, Execution, Deployment -> Build Tools -> Gradle
 * https://stackoverflow.com/a/58125215/5279996
 */
/*
// Use this task OR from the IDE build and run tests using Intellij IDEA in Build, Execution, Deployment -> Build Tools -> Gradle
tasks.test {
    useJUnitPlatform()
}*/