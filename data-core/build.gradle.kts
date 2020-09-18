group = "app.mobius"
version = "0.0.0"

repositories {
}

/**
 * https://mvnrepository.com/artifact/org.hibernate/hibernate-core
 */
dependencies {
    implementation(project(":domain"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    implementation("org.hibernate",  "hibernate-core",  "5.4.21.Final")

    testImplementation("junit", "junit", "4.12")
}
