group = "app.mobius"
version = "0.0.0"

/**
 * Sources:
 *  https://github.com/ronmamo/reflections
 *  https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
 *  https://mvnrepository.com/artifact/org.springframework/spring-orm
 *  https://mvnrepository.com/artifact/org.springframework/spring-jdbc
 */
dependencies {
    api(project(":domain"))

    implementation("org.reflections:reflections:0.9.12")

    api("org.springframework.data", "spring-data-jpa", "2.3.4.RELEASE")
    implementation("org.springframework", "spring-orm", "5.2.9.RELEASE")
    implementation("org.springframework", "spring-jdbc", "5.2.9.RELEASE")

    implementation(project(":library_base"))
    implementation(project(":library_test_utils"))
    implementation(kotlin("reflect"))
}