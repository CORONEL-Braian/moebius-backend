group = "app.mobius"
version = "0.0.0"

repositories {
}

/**
 * Sources:
 *  https://mvnrepository.com/artifact/org.hibernate/hibernate-core
 *  https://mvnrepository.com/artifact/org.postgresql/postgresql
 */
dependencies {
    implementation(project(":domain"))

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))

    implementation("org.hibernate",  "hibernate-core",  "5.4.21.Final")
    implementation("org.postgresql",  "postgresql",  "42.2.16")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}