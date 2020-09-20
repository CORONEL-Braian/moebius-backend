group = "app.möbius"
version = "0.0.0"

/**
 * Precondition: Run tests using Intellij IDEA in
 *  Settings > Build, Execution, Deployment > Build Tools > Gradle
 * Source:
 *  https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
 */
dependencies {
    testApi("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}