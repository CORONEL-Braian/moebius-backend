group = "org.itdevexpert.jsonApi"
version = "0.0.0"

/**
 * Jackson: https://search.maven.org/classic/#search%7Cgav%7C1%7Cg%3A%22com.fasterxml.jackson.core%22%20AND%20a%3A%22jackson-databind%22
 * Objenesis: https://mvnrepository.com/artifact/org.objenesis/objenesis
 */
dependencies {
    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.6.7.4")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("org.objenesis","objenesis", "3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.0")
}

// For library
tasks.test {
    useJUnitPlatform()
}