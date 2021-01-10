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
    implementation("org.springframework.boot", "spring-boot-starter-test", "2.4.1")

    runtimeOnly("io.mockk:mockk:1.10.0")
    runtimeOnly("org.junit.jupiter", "junit-jupiter-api", "5.6.0")
    runtimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.0")
    runtimeOnly("org.mockito", "mockito-junit-jupiter", "3.2.4")
    runtimeOnly("org.springframework.boot", "spring-boot-starter-test", "2.4.1")
}