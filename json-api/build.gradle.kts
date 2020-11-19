version = "0.0.0"

/**
 * Jackson: https://search.maven.org/classic/#search%7Cgav%7C1%7Cg%3A%22com.fasterxml.jackson.core%22%20AND%20a%3A%22jackson-databind%22
 */
dependencies {
    implementation(project(":library_test_utils"))

    implementation("com.fasterxml.jackson.core", "jackson-databind", "2.6.7.4")
}