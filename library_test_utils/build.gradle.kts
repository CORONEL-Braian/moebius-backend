group = "app.möbius"
version = "0.0.0"

repositories {
}

/**
 * Precondition: Run tests using Intellij IDEA in
 *  Settings > Build, Execution, Deployment > Build Tools > Gradle
 * Source:
 *  https://docs.gradle.org/current/userguide/java_testing.html#using_junit5
 */
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}